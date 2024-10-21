package ampersand.squirrel.event.application

import ampersand.squirrel.event.ActiveType
import ampersand.squirrel.event.DotoriEventRepository
import ampersand.squirrel.event.EventType
import ampersand.squirrel.event.data.MusicLog
import ampersand.squirrel.event.data.ReserveLog
import ampersand.squirrel.event.payload.MusicDotoriEventPayloadRepository
import ampersand.squirrel.event.payload.TotalDotoriEventPayloadRepository
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class DotoriEventReader(
    private val dotoriEventRepository: DotoriEventRepository,
    private val musicDotoriEventPayloadRepository: MusicDotoriEventPayloadRepository,
    private val totalDotoriEventPayloadRepository: TotalDotoriEventPayloadRepository
) {

    fun queryMusicEvent(eventType: EventType, date: LocalDateTime, activeType: ActiveType): MusicLog {
        TODO("IMPL")
    }

    fun queryReserveEvent(eventType: EventType, date: LocalDateTime, activeType: ActiveType): ReserveLog {
        TODO("IMPL")
    }
}