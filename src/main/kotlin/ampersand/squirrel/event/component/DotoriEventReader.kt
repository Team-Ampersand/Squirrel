package ampersand.squirrel.event.component

import ampersand.squirrel.event.ActiveType
import ampersand.squirrel.event.DotoriEvent
import ampersand.squirrel.event.DotoriEventRepository
import ampersand.squirrel.event.EventType
import ampersand.squirrel.event.data.MusicDotoriEventResponse
import ampersand.squirrel.event.data.ReserveDotoriEventResponse
import ampersand.squirrel.event.payload.MusicDotoriEventPayloadRepository
import ampersand.squirrel.global.error.BasicException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class DotoriEventReader(
    private val dotoriEventRepository: DotoriEventRepository,
    private val musicDotoriEventPayloadRepository: MusicDotoriEventPayloadRepository,
) {

    fun queryMusicEvent(eventType: EventType, date: LocalDate, activeType: ActiveType?): List<MusicDotoriEventResponse> {
        val musicLogs = when(eventType) {
            EventType.MUSIC -> fetchMusicLogs(date, activeType)
            EventType.LIKE -> fetchLikeLogs(date, activeType)
            EventType.MUSIC_ALL -> {
                val music = fetchMusicLogs(date, activeType)
                val like = fetchLikeLogs(date, activeType)
                union(music, like)
            }
            else -> throw BasicException("Invalid EventType Request Music!", HttpStatus.BAD_REQUEST.value())
        }

        musicLogs.earlyReturnIfEmpty()

        val createTotal = musicLogs.count { event -> event.activeType == ActiveType.CREATE }
        val deleteTotal = musicLogs.count { event -> event.activeType == ActiveType.DELETE }
        val total = musicLogs.count()

        return musicLogs.mapIndexed { idx, event ->
            MusicDotoriEventResponse(
                logId = event.id!!,
                offset = idx + 1,
                username = event.username,
                createdAt = event.createdAt,
                env = event.env,
                activeType = event.activeType,
                musicTitle = musicDotoriEventPayloadRepository.findByEventId(event.id)?.musicTitle
                    ?: throw BasicException("Not Found Music Payload", HttpStatus.NOT_FOUND.value()),
                eventType = event.eventType,
                createTotal = createTotal,
                deleteTotal = deleteTotal,
                total = total
            )
        }
    }

    private fun fetchMusicLogs(date: LocalDate, activeType: ActiveType?): List<DotoriEvent> {
        return activeType?.let {
            dotoriEventRepository.findMusicLogWithActiveType(date.year, date.monthValue, date.dayOfMonth, it)
        } ?: dotoriEventRepository.findMusicLogWithoutActiveType(date.year, date.monthValue, date.dayOfMonth)
    }

    private fun fetchLikeLogs(date: LocalDate, activeType: ActiveType?): List<DotoriEvent> {
        return activeType?.let {
            dotoriEventRepository.findLikeLogWithActiveType(date.year, date.monthValue, date.dayOfMonth, it)
        } ?: dotoriEventRepository.findLikeLogWithoutActiveType(date.year, date.monthValue, date.dayOfMonth)
    }


    private fun union(music: List<DotoriEvent>, like: List<DotoriEvent>): List<DotoriEvent> {
        val result = music + like
        return result.sortedBy { it.createdAt }
    }

    fun queryReserveEvent(eventType: EventType, date: LocalDate, activeType: ActiveType?): List<ReserveDotoriEventResponse> {
        val reserveLogs = when(eventType) {
            EventType.SELFSTUDY -> fetchSelfStudyLogs(date, activeType)
            EventType.MASSAGE -> fetchMassageLogs(date, activeType)
            EventType.RESERVE_ALL -> {
                val selfStudy = fetchSelfStudyLogs(date, activeType)
                val massage = fetchMassageLogs(date, activeType)
                union(selfStudy, massage)
            }
            else -> throw BasicException("Invalid EventType Request Reserve!", HttpStatus.BAD_REQUEST.value())
        }

        reserveLogs.earlyReturnIfEmpty()

        val createTotal = reserveLogs.count { event -> event.activeType == ActiveType.CREATE }
        val deleteTotal = reserveLogs.count { event -> event.activeType == ActiveType.DELETE }
        val total = reserveLogs.count()

        return reserveLogs.mapIndexed { idx, event ->
            ReserveDotoriEventResponse(
                logId = event.id!!,
                offset = idx + 1,
                username = event.username,
                createdAt = event.createdAt,
                env = event.env,
                activeType = event.activeType,
                eventType = event.eventType,
                createTotal = createTotal,
                deleteTotal = deleteTotal,
                total = total
            )
        }
    }

    private fun fetchSelfStudyLogs(date: LocalDate, activeType: ActiveType?): List<DotoriEvent> {
        return activeType?.let {
            dotoriEventRepository.findSelfStudyLogsWithActiveType(date.year, date.monthValue, date.dayOfMonth, it)
        } ?: dotoriEventRepository.findSelfStudyLogsWithoutActiveType(date.year, date.monthValue, date.dayOfMonth)
    }

    private fun fetchMassageLogs(date: LocalDate, activeType: ActiveType?): List<DotoriEvent> {
        return activeType?.let {
            dotoriEventRepository.findMassageLogsWithActiveType(date.year, date.monthValue, date.dayOfMonth, it)
        } ?: dotoriEventRepository.findMassageLogsWithoutActiveType(date.year, date.monthValue, date.dayOfMonth)
    }

    fun<T> List<T>?.earlyReturnIfEmpty(): ArrayList<T> {
        return this?.let { ArrayList(it) } ?: ArrayList()
    }

}
