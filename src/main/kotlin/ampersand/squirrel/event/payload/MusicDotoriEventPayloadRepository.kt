package ampersand.squirrel.event.payload

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query

interface MusicDotoriEventPayloadRepository : MongoRepository<MusicDotoriEventPayload, String> {
    fun findByEventId(eventId: String): MusicDotoriEventPayload?
}