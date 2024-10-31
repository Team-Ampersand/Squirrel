package ampersand.squirrel.event.application

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
import java.time.LocalDateTime

@Component
class DotoriEventReader(
    private val dotoriEventRepository: DotoriEventRepository,
    private val musicDotoriEventPayloadRepository: MusicDotoriEventPayloadRepository,
) {

    fun queryMusicEvent(eventType: EventType, date: LocalDate, activeType: ActiveType?): List<MusicDotoriEventResponse> {
        val musicLogs = when(eventType) {
            EventType.MUSIC -> {
                dotoriEventRepository.findMusicLog(date.year, date.monthValue, date.dayOfMonth, activeType)
            }
            EventType.LIKE -> {
                dotoriEventRepository.findLikeLog(date.year, date.monthValue, date.dayOfMonth, activeType)
            }
            EventType.MUSIC_ALL -> {
                val music = dotoriEventRepository.findMusicLog(date.year, date.monthValue, date.dayOfMonth, activeType)
                val like = dotoriEventRepository.findLikeLog(date.year, date.monthValue, date.dayOfMonth, activeType)
                union(music, like)
            }
            else -> throw BasicException("Invalid EventType Request Music!", HttpStatus.BAD_REQUEST.value())
        }
        val payloads = musicDotoriEventPayloadRepository.findAllByEventIdIn(musicLogs.map { it.id })
        val musicLogMap = payloads.associateBy { it.eventId }
        val createTotal = musicLogs.count { event -> event.activeType == ActiveType.CREATE }
        val deleteTotal = musicLogs.count { event -> event.activeType == ActiveType.DELETE }
        val total = musicLogs.count()
        return musicLogs.mapIndexed { idx, event ->
            MusicDotoriEventResponse(
                logId = event.id,
                offset = idx + 1,
                username = event.username,
                createdAt = event.createdAt,
                env = event.env,
                activeType = event.activeType,
                musicTitle = musicLogMap[event.id]!!.musicTitle,
                eventType = event.eventType,
                createTotal = createTotal,
                deleteTotal = deleteTotal,
                total = total
            )
        }
    }

    private fun union(music: List<DotoriEvent>, like: List<DotoriEvent>): List<DotoriEvent> {
        val result = music + like
        return result.sortedBy { it.createdAt }
    }

    fun queryReserveEvent(eventType: EventType, date: LocalDate, activeType: ActiveType?): List<ReserveDotoriEventResponse> {
        val reserveLogs = when(eventType) {
            EventType.SELFSTUDY -> {
                dotoriEventRepository.findSelfStudyLog(date.year, date.monthValue, date.dayOfMonth, activeType)
            }
            EventType.LIKE -> {
                dotoriEventRepository.findMassageLog(date.year, date.monthValue, date.dayOfMonth, activeType)
            }
            EventType.RESERVE_ALL -> {
                val selfStudy = dotoriEventRepository.findSelfStudyLog(date.year, date.monthValue, date.dayOfMonth, activeType)
                val massage = dotoriEventRepository.findSelfStudyLog(date.year, date.monthValue, date.dayOfMonth, activeType)
                union(selfStudy, massage)
            }
            else -> throw BasicException("Invalid EventType Request Reserve!", HttpStatus.BAD_REQUEST.value())
        }

        val createTotal = reserveLogs.count { event -> event.activeType == ActiveType.CREATE }
        val deleteTotal = reserveLogs.count { event -> event.activeType == ActiveType.DELETE }
        val total = reserveLogs.count()

        return reserveLogs.mapIndexed { idx, event ->
            ReserveDotoriEventResponse(
                logId = event.id,
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
}