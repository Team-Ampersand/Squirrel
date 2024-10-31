package ampersand.squirrel.event.application

import ampersand.squirrel.event.ActiveType
import ampersand.squirrel.event.EventType
import ampersand.squirrel.event.data.MusicLog
import ampersand.squirrel.event.data.ReserveLog
import java.time.LocalDate
import java.time.LocalDateTime

interface QueryDotoriEventService {
    fun queryMusicEvent(eventType: EventType, date: LocalDate, activeType: ActiveType?): MusicLog
    fun queryReserveEvent(eventType: EventType, date: LocalDate, activeType: ActiveType?): ReserveLog
}