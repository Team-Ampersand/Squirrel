package ampersand.squirrel.event.payload

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.lang.IllegalStateException

@Document
class MusicDotoriEventPayload(
    @Id
    val _id: String? = null,
    val musicTitle: String,
    @Indexed(unique = true)
    val eventId: String
) {
    val id: String
        get() {
            return id ?: throw IllegalStateException("Id Must not be null")
        }
}