package ampersand.squirrel.event.fake

import com.fasterxml.jackson.databind.ObjectMapper
import io.awspring.cloud.sqs.operations.SendResult
import io.awspring.cloud.sqs.operations.SqsTemplate
import org.springframework.stereotype.Component

@Component
class FakeSquirrelEventProducer(
    private val objectMapper: ObjectMapper,
    private val sqsTemplate: SqsTemplate
) : EventProducer {

    override fun publishEvent(queue: String, event: Any, eventType: String): SendResult<String> {
        return sqsTemplate.send { sendOpsTo ->
            sendOpsTo
                .queue(queue)
                .header("eventType", eventType)
                .payload(objectMapper.writeValueAsString(event))
        }
    }
}