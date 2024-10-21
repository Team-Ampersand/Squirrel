package ampersand.squirrel.event.payload

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document
class TotalDotoriEventPayload(
    @Id
    val id: String? = null,
    val eventId: String,
    val date: LocalDateTime,
    val createTotal: Int,
    val deleteTotal: Int,
    val total: Int
)