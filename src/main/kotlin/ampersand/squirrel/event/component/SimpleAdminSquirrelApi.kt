package ampersand.squirrel.event.component

import ampersand.squirrel.event.*
import ampersand.squirrel.event.payload.MusicDotoriEventPayload
import ampersand.squirrel.event.payload.MusicDotoriEventPayloadRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class SimpleAdminSquirrelApi(
    private val dotoriEventRepository: DotoriEventRepository,
    private val musicDotoriEventPayloadRepository: MusicDotoriEventPayloadRepository
) {

    fun createEvent() {
        val musicEvent1 = DotoriEvent(
            id = "music_id_value",
            username = "hope",
            createdYear = 2024,
            createdMonth = 2,
            createdDay = 6,
            createdAt = LocalDateTime.of(2024, 2, 6, 0,0),
            env = EventEnv.PROD,
            activeType = ActiveType.CREATE,
            eventType = EventType.MUSIC
        )
        val musicPayload1 = MusicDotoriEventPayload(
            id = "music_payload_id_value",
            musicTitle = "redoor - love scala",
            eventId = "music_id_value"
        )

        dotoriEventRepository.save(musicEvent1)
        musicDotoriEventPayloadRepository.save(musicPayload1)
    }

}