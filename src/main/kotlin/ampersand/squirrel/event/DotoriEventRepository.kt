package ampersand.squirrel.event

import org.springframework.data.mongodb.repository.MongoRepository

interface DotoriEventRepository : MongoRepository<DotoriEvent, String> {
}