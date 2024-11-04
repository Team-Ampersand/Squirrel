package ampersand.squirrel.event.data

import ampersand.squirrel.event.ActiveType
import ampersand.squirrel.event.EventEnv
import ampersand.squirrel.event.EventType
import java.time.LocalDateTime

data class MusicDotoriEvent(
    val id: String,
    val offset: Int,
    val username: String,
    val createdAt: LocalDateTime,
    val env: EventEnv,
    val activeType: ActiveType,
    val eventType: EventType,
    val musicTitle: String,
)

data class ReserveDotoriEvent(
    val id: String,
    val offset: Int,
    val username: String,
    val createdAt: LocalDateTime,
    val env: EventEnv,
    val activeType: ActiveType,
    val eventType: EventType,
)