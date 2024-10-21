package ampersand.squirrel.event.payload

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
class MusicDotoriEventPayload(
    @Id
    val id: String? = null,
    val title: String,
    val eventId: String
)