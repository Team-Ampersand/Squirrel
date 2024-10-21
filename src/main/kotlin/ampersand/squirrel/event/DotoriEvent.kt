package ampersand.squirrel.event

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document
class DotoriEvent(
    @Id
    val id: String? = null,
    val username: String,
    val createdAt: LocalDateTime,
    val env: EventEnv,
    val activeType:  ActiveType
)

enum class EventType(
    val description: String
) {
    MUSIC("음악 신청"), LIKE("좋아요"),
    MASSAGE("안마 의자 신청"), SELFSTUDY("자습 신청")
}

enum class EventEnv(
    val description: String
) {
    DEV("개발서버"),
    PROD("운영서버")
}

enum class ActiveType(
    val description: String
) {
    CREATE("생성 연산"),
    DELETE("삭제 연ㅇ산")
}