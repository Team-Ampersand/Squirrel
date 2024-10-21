package ampersand.squirrel.event.application

import ampersand.squirrel.event.ActiveType
import ampersand.squirrel.event.EventType
import ampersand.squirrel.event.data.MusicLog
import ampersand.squirrel.event.data.ReserveLog
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class QueryDotoriEventServiceImpl(
    private val dotoriEventReader: DotoriEventReader
) : QueryDotoriEventService {

    override fun queryMusicEvent(eventType: EventType, date: LocalDateTime, activeType: ActiveType): MusicLog {
        TODO("Not yet implemented")
    }

    override fun queryReserveEvent(eventType: EventType, date: LocalDateTime, activeType: ActiveType): ReserveLog {
        TODO("Not yet implemented")
    }

}
