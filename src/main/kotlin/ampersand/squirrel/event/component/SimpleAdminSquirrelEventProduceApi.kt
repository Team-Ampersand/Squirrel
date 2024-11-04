package ampersand.squirrel.event.component

import ampersand.squirrel.event.ActiveType
import ampersand.squirrel.event.EventEnv
import ampersand.squirrel.event.EventType
import ampersand.squirrel.event.data.MusicDotoriEvent
import ampersand.squirrel.event.data.ReserveDotoriEvent
import ampersand.squirrel.event.fake.EventProducer
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.*

@Component
class SimpleAdminSquirrelEventProduceApi(
    private val eventProducer: EventProducer
) {

    fun simpleMusicEventProduce() {
        val event = MusicDotoriEvent(
            id = UUID.randomUUID().toString(),
            username = "k hope",
            createdAt = LocalDateTime.now(),
            env = EventEnv.PROD,
            activeType = ActiveType.CREATE,
            eventType = EventType.MUSIC,
            musicTitle = "redoor love scala"
        )
        eventProducer.publishEvent("music-squirrel-event", event, "MUSIC")
    }

    fun simpleReserveEvent() {
        val event = ReserveDotoriEvent(
            id = UUID.randomUUID().toString(),
            username = "k hope",
            createdAt = LocalDateTime.now(),
            env = EventEnv.PROD,
            activeType = ActiveType.CREATE,
            eventType = EventType.MUSIC,
        )
        eventProducer.publishEvent("reserve-squirrel-event", event, "RESERVE")
    }
}