package ampersand.squirrel.event.listener

import ampersand.squirrel.event.component.EventProcessor
import ampersand.squirrel.event.data.MusicDotoriEvent
import ampersand.squirrel.event.data.ReserveDotoriEvent
import com.fasterxml.jackson.databind.ObjectMapper
import io.awspring.cloud.sqs.annotation.SqsListener
import io.awspring.cloud.sqs.listener.acknowledgement.Acknowledgement
import org.springframework.messaging.MessageHeaders
import org.springframework.messaging.handler.annotation.Headers
import org.springframework.stereotype.Component
import java.lang.IllegalArgumentException

@Component
class EventListener(
    private val eventProcessor: EventProcessor,
    private val objectMapper: ObjectMapper
) {

    @SqsListener(value = ["squirrel-sqs"])
    fun listen(payload: String, @Headers headers: MessageHeaders, acknowledgement: Acknowledgement) {

        when(headers["eventType"].toString()) {
            "MUSIC" -> {
                val event = objectMapper.readValue(payload, MusicDotoriEvent::class.java)
                eventProcessor.processMusicEvent(event)
            }

            "RESERVE" -> {
                val event = objectMapper.readValue(payload, ReserveDotoriEvent::class.java)
                eventProcessor.processReserveEvent(event)
            }

            else -> {
                throw IllegalArgumentException("Event Type is wrong!!")
            }
        }
        acknowledgement.acknowledge()
    }
}