package ampersand.squirrel.event.listener

import ampersand.squirrel.event.application.EventProcessor
import ampersand.squirrel.event.data.MusicDotoriEvent
import ampersand.squirrel.event.data.ReserveDotoriEvent
import com.fasterxml.jackson.databind.ObjectMapper
import io.awspring.cloud.sqs.annotation.SqsListener
import io.awspring.cloud.sqs.listener.acknowledgement.Acknowledgement
import org.springframework.messaging.MessageHeaders
import org.springframework.messaging.handler.annotation.Headers
import org.springframework.stereotype.Component

@Component
class EventListener(
    private val eventProcessor: EventProcessor,
    private val objectMapper: ObjectMapper
) {

    @SqsListener(value = ["music-squirrel-event"])
    fun musicListen(payload: String, @Headers headers: MessageHeaders, acknowledgement: Acknowledgement) {
        val event = objectMapper.readValue(payload, MusicDotoriEvent::class.java)
        eventProcessor.processMusicEvent(event)
        acknowledgement.acknowledge()
    }

    @SqsListener(value = ["reserve-squirrel-event"])
    fun reserveListen(payload: String, @Headers headers: MessageHeaders, acknowledgement: Acknowledgement) {
        val event = objectMapper.readValue(payload, ReserveDotoriEvent::class.java)
        eventProcessor.processReserveEvent(event)
        acknowledgement.acknowledge()
    }
}