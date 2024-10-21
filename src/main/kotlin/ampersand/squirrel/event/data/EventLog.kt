package ampersand.squirrel.event.data

import ampersand.squirrel.event.ActiveType
import ampersand.squirrel.event.EventEnv
import ampersand.squirrel.event.EventType
import java.time.LocalDateTime

data class MusicLog(
    val musicLog: List<MusicDotoriEventResponse>
)

data class ReserveLog(
    val reserveLog: List<ReserveDotoriEventResponse>
)

data class MusicDotoriEventResponse(
    val logId: String,
    val offset: Int,
    val username: String,
    val createdAt: LocalDateTime,
    val env: EventEnv,
    val activeType: ActiveType,
    val eventType: EventType,
    val musicTitle: String,
    val createTotal: Int,
    val deleteTotal: Int,
    val total: Int
)

data class ReserveDotoriEventResponse(
    val logId: String,
    val offset: Int,
    val username: String,
    val createdAt: LocalDateTime,
    val env: EventEnv,
    val activeType: ActiveType,
    val eventType: EventType,
    val createTotal: Int,
    val deleteTotal: Int,
    val total: Int
)
