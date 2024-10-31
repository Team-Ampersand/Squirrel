package ampersand.squirrel.event.application

import ampersand.squirrel.event.ActiveType
import ampersand.squirrel.event.EventType
import ampersand.squirrel.event.data.MusicLog
import ampersand.squirrel.event.data.ReserveLog
import java.time.LocalDateTime

interface QueryDotoriEventService {
    fun queryMusicEvent(eventType: EventType, date: LocalDateTime, activeType: ActiveType?): MusicLog
    fun queryReserveEvent(eventType: EventType, date: LocalDateTime, activeType: ActiveType?): ReserveLog
}