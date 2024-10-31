package ampersand.squirrel.event

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query

interface DotoriEventRepository : MongoRepository<DotoriEvent, String> {
    @Query("""
        {
          'createdYear': ?0, 'createdMonth': ?1, 'createdDay': ?2, 
          'activeType': { ${'$'}in: [?3, null] }, 'eventType': 'MUSIC',
        }
        """,
        sort = "{ 'createdAt': 1 }"
    )
    fun findMusicLog(year: Int, month: Int, day: Int, activeType: ActiveType?): List<DotoriEvent>

    @Query("""
       {'createdYear': ?0, 'createdMonth': ?1, 'createdDay': ?2, 
     'activeType': { ${'$'}in: [?3, null] }, 'eventType': 'LIKE' }
        """,
        sort = "{ 'createdAt': 1 }"
    )
    fun findLikeLog(year: Int, month: Int, day: Int, activeType: ActiveType?): List<DotoriEvent>

    @Query("""
        {
          'createdYear': ?0, 'createdMonth': ?1, 'createdDay': ?2, 
          'activeType': { ${'$'}in: [?3, null] }, 'eventType': 'SELFSTUDY',
        }
        """,
        sort = "{ 'createdAt': 1 }"
    )
    fun findSelfStudyLog(year: Int, month: Int, day: Int, activeType: ActiveType?): List<DotoriEvent>

    @Query("""
       {'createdYear': ?0, 'createdMonth': ?1, 'createdDay': ?2, 
     'activeType': { ${'$'}in: [?3, null] }, 'eventType': 'MASSAGE' }
        """,
        sort = "{ 'createdAt': 1 }"
    )
    fun findMassageLog(year: Int, month: Int, day: Int, activeType: ActiveType?): List<DotoriEvent>
}