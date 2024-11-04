package ampersand.squirrel.event.application

import ampersand.squirrel.event.DotoriEvent
import ampersand.squirrel.event.DotoriEventRepository
import ampersand.squirrel.event.EventType
import ampersand.squirrel.event.data.MusicDotoriEvent
import ampersand.squirrel.event.data.ReserveDotoriEvent
import ampersand.squirrel.event.payload.MusicDotoriEventPayload
import ampersand.squirrel.event.payload.MusicDotoriEventPayloadRepository
import ampersand.squirrel.global.error.BasicException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component

@Component
class EventProcessor(
    private val dotoriEventRepository: DotoriEventRepository,
    private val musicDotoriEventPayloadRepository: MusicDotoriEventPayloadRepository
) {

    private val log = LoggerFactory.getLogger(this.javaClass.simpleName)

    fun processMusicEvent(event: MusicDotoriEvent) {
        if(event.eventType != EventType.MUSIC && event.eventType != EventType.LIKE)
            throw BasicException("Can't save an event Because Type of Event is not Music or Like", HttpStatus.BAD_REQUEST.value())

        val dotoriEvent = DotoriEvent(
            username = event.username,
            createdAt = event.createdAt,
            createdYear = event.createdAt.year,
            createdMonth = event.createdAt.monthValue,
            createdDay = event.createdAt.dayOfMonth,
            env = event.env,
            activeType = event.activeType,
            eventType = event.eventType
        )
        val saved = dotoriEventRepository.save(dotoriEvent)
        val musicPayload = MusicDotoriEventPayload(
            musicTitle = event.musicTitle,
            eventId = saved.id!!
        )
        val savedPayload = musicDotoriEventPayloadRepository.save(musicPayload)
        log.info("Success Save Music Event id = ${saved.id}, ${savedPayload.id}")
    }

    fun processReserveEvent(event: ReserveDotoriEvent) {
        if(event.eventType != EventType.SELFSTUDY && event.eventType != EventType.MASSAGE)
            throw BasicException("Can't save an event Because Type of Event is not Music or Like", HttpStatus.BAD_REQUEST.value())

        val dotoriEvent = DotoriEvent(
            username = event.username,
            createdAt = event.createdAt,
            createdYear = event.createdAt.year,
            createdMonth = event.createdAt.monthValue,
            createdDay = event.createdAt.dayOfMonth,
            env = event.env,
            activeType = event.activeType,
            eventType = event.eventType
        )
        val saved = dotoriEventRepository.save(dotoriEvent)
        log.info("Success Save Reserve Event id = ${saved.id}")
    }
}