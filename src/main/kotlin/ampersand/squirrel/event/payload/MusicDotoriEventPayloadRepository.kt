package ampersand.squirrel.event.payload

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query

interface MusicDotoriEventPayloadRepository : MongoRepository<MusicDotoriEventPayload, String> {
    @Query("""
        { 
            'eventId': { ${'$'} in : ?0 }
        }
    """)
    fun findAllByEventIdIn(eventIds: List<String>): List<MusicDotoriEventPayload>
}