package ampersand.squirrel.global.error

open class BasicException(
    override val message: String,
    val status: Int
) : RuntimeException(message)