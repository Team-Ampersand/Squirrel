package ampersand.squirrel.event.api

import ampersand.squirrel.event.component.SimpleAdminSquirrelApi
import ampersand.squirrel.event.component.SimpleAdminSquirrelEventProduceApi
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/admin")
class AdminController(
    private val simpleAdminSquirrelApi: SimpleAdminSquirrelApi,
    private val simpleAdminSquirrelEventProduceApi: SimpleAdminSquirrelEventProduceApi
) {

    @GetMapping("/dummy")
    fun createDummyTrigger(): ResponseEntity<Void> {
        simpleAdminSquirrelApi.createEvent()
        return ResponseEntity.ok().build()
    }

    @GetMapping("/music-event")
    fun produceMusicEventTrigger(): ResponseEntity<Void> {
        simpleAdminSquirrelEventProduceApi.simpleMusicEventProduce()
        return ResponseEntity.ok().build()
    }

    @GetMapping("/reserve-event")
    fun produceReserveEventTrigger(): ResponseEntity<Void> {
        simpleAdminSquirrelEventProduceApi.simpleReserveEvent()
        return ResponseEntity.ok().build()
    }
}