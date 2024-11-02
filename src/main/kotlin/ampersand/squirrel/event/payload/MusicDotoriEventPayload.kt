package ampersand.squirrel.event.payload

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.lang.IllegalStateException

@Document("music_payload")
class MusicDotoriEventPayload(
    @Id
    val id: String? = null,
    val musicTitle: String,
    @Indexed(unique = true)
    val eventId: String
)