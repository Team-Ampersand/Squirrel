package ampersand.squirrel.event.controller

import ampersand.squirrel.event.ActiveType
import ampersand.squirrel.event.EventType
import ampersand.squirrel.event.application.QueryDotoriEventService
import ampersand.squirrel.event.data.MusicLog
import ampersand.squirrel.event.data.ReserveLog
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
class EventController(
    private val queryDotoriEventService: QueryDotoriEventService
) {

    @GetMapping("/music")
    fun queryMusicLog(@RequestParam musicLogType: EventType, @RequestParam(
        value = "date",
        required = true
    ) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) date: LocalDate, @RequestParam activeType: ActiveType?): ResponseEntity<MusicLog> {
        val response = queryDotoriEventService.queryMusicEvent(eventType = musicLogType, date = date, activeType = activeType )
        return ResponseEntity.ok(response)
    }

    @GetMapping("/reserve")
    fun queryReserveLog(@RequestParam musicLogType: EventType, @RequestParam(
        value = "date",
        required = true
    ) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) date: LocalDate, @RequestParam activeType: ActiveType?): ResponseEntity<ReserveLog> {
        val response = queryDotoriEventService.queryReserveEvent(eventType = musicLogType, date = date, activeType = activeType)
        return ResponseEntity.ok(response)
    }
}