package ampersand.squirrel.event.fake

import io.awspring.cloud.sqs.operations.SendResult


interface EventProducer {
    fun publishEvent(event: Any, eventType: String): SendResult<String>
}