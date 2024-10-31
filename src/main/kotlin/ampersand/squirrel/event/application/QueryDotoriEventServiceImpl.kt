package ampersand.squirrel.event.application

import ampersand.squirrel.event.ActiveType
import ampersand.squirrel.event.EventType
import ampersand.squirrel.event.data.MusicLog
import ampersand.squirrel.event.data.ReserveLog
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class QueryDotoriEventServiceImpl(
    private val dotoriEventReader: DotoriEventReader
) : QueryDotoriEventService {

    override fun queryMusicEvent(eventType: EventType, date: LocalDate, activeType: ActiveType?): MusicLog {
        val events = dotoriEventReader.queryMusicEvent(eventType, date, activeType)
        return MusicLog(events)
    }

    override fun queryReserveEvent(eventType: EventType, date: LocalDate, activeType: ActiveType?): ReserveLog {
        val events = dotoriEventReader.queryReserveEvent(eventType, date, activeType)
        return ReserveLog(events)
    }

}
