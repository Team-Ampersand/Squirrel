package ampersand.squirrel.event.fake

import ampersand.squirrel.event.data.MusicDotoriEvent
import ampersand.squirrel.event.data.ReserveDotoriEvent
import io.awspring.cloud.sqs.operations.SendResult


interface EventProducer {
    fun publishMusicEvent(topic: String, event: MusicDotoriEvent): SendResult<String>
    fun publishReserveEvent(topic: String, event: ReserveDotoriEvent): SendResult<String>
}