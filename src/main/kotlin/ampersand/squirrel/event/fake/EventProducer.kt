package ampersand.squirrel.event.fake

import io.awspring.cloud.sqs.operations.SendResult


interface EventProducer {
    fun publishEvent(queue: String, event: Any): SendResult<String>
}