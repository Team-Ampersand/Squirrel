package ampersand.squirrel.event.controller

import ampersand.squirrel.event.application.SimpleAdminSquirrelApi
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/admin")
class AdminController(
    private val simpleAdminSquirrelApi: SimpleAdminSquirrelApi
) {

    @GetMapping("/dummy")
    fun createDummy(): ResponseEntity<Void> {
        simpleAdminSquirrelApi.createEvent()
        return ResponseEntity.ok().build()
    }
}