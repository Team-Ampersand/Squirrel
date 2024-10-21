package ampersand.squirrel.event.payload

import org.springframework.data.mongodb.repository.MongoRepository

interface TotalDotoriEventPayloadRepository : MongoRepository<TotalDotoriEventPayload, String> {
}