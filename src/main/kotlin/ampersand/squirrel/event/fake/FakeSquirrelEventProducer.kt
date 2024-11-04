package ampersand.squirrel.event.fake

import ampersand.squirrel.event.data.MusicDotoriEvent
import ampersand.squirrel.event.data.ReserveDotoriEvent
import com.fasterxml.jackson.databind.ObjectMapper
import io.awspring.cloud.sqs.operations.SendResult
import io.awspring.cloud.sqs.operations.SqsTemplate
import org.springframework.stereotype.Component

@Component
class FakeSquirrelEventProducer(
    private val objectMapper: ObjectMapper,
    private val sqsTemplate: SqsTemplate
) : EventProducer {

    override fun publishMusicEvent(topic: String, event: MusicDotoriEvent): SendResult<String> {
        return sqsTemplate.send { sendOpsTo ->
            sendOpsTo
                .queue("music-squirrel-event")
                .payload(objectMapper.writeValueAsString(event))
        }
    }

    override fun publishReserveEvent(topic: String, event: ReserveDotoriEvent): SendResult<String> {
        return sqsTemplate.send { sendOpsTo ->
            sendOpsTo
                .queue("reserve-squirrel-event")
                .payload(objectMapper.writeValueAsString(event))
        }
    }
}