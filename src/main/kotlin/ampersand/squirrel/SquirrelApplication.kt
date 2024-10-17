package ampersand.squirrel

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SquirrelApplication

fun main(args: Array<String>) {
	runApplication<SquirrelApplication>(*args)
}
