package ampersand.squirrel.event

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document("dotori_event")
class DotoriEvent(
    @Id
    val id: String? = null,
    val username: String,
    val createdYear: Int,
    val createdMonth: Int,
    val createdDay: Int,
    val createdAt: LocalDateTime,
    val env: EventEnv,
    val activeType:  ActiveType,
    val eventType: EventType
)

enum class EventType(
    val description: String
) {
    MUSIC("음악 신청"), LIKE("좋아요"), MUSIC_ALL("음악 로그 전체"),
    MASSAGE("안마 의자 신청"), SELFSTUDY("자습 신청"), RESERVE_ALL("예약 로그 전체")
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