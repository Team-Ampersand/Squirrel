package ampersand.squirrel.event

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query

interface DotoriEventRepository : MongoRepository<DotoriEvent, String> {

    @Query("""
    {
      'createdYear': ?0, 'createdMonth': ?1, 'createdDay': ?2, 
      'activeType': ?3,
      'eventType': 'MUSIC'
    }
    """, sort = "{ 'createdAt': 1 }")
    fun findMusicLogWithActiveType(year: Int, month: Int, day: Int, activeType: ActiveType): List<DotoriEvent>

    @Query("""
    {
      'createdYear': ?0, 'createdMonth': ?1, 'createdDay': ?2, 
      'eventType': 'MUSIC'
    }
    """, sort = "{ 'createdAt': 1 }")
    fun findMusicLogWithoutActiveType(year: Int, month: Int, day: Int): List<DotoriEvent>

    @Query("""
    {
      'createdYear': ?0, 'createdMonth': ?1, 'createdDay': ?2, 
      'activeType': ?3,
      'eventType': 'LIKE'
    }
    """, sort = "{ 'createdAt': 1 }")
    fun findLikeLogWithActiveType(year: Int, month: Int, day: Int, activeType: ActiveType): List<DotoriEvent>

    @Query("""
    {
      'createdYear': ?0, 'createdMonth': ?1, 'createdDay': ?2, 
      'eventType': 'LIKE'
    }
    """, sort = "{ 'createdAt': 1 }")
    fun findLikeLogWithoutActiveType(year: Int, month: Int, day: Int): List<DotoriEvent>

    @Query("""
    {
      'createdYear': ?0, 'createdMonth': ?1, 'createdDay': ?2, 
      'activeType': ?3,
      'eventType': 'SELFSTUDY'
    }
    """, sort = "{ 'createdAt': 1 }")
    fun findSelfStudyLogsWithActiveType(year: Int, month: Int, day: Int, activeType: ActiveType): List<DotoriEvent>

    @Query("""
    {
      'createdYear': ?0, 'createdMonth': ?1, 'createdDay': ?2, 
      'eventType': 'SELFSTUDY'
    }
    """, sort = "{ 'createdAt': 1 }")
    fun findSelfStudyLogsWithoutActiveType(year: Int, month: Int, day: Int): List<DotoriEvent>

    @Query("""
    {
      'createdYear': ?0, 'createdMonth': ?1, 'createdDay': ?2, 
      'activeType': ?3,
      'eventType': 'MASSAGE'
    }
    """, sort = "{ 'createdAt': 1 }")
    fun findMassageLogsWithActiveType(year: Int, month: Int, day: Int, activeType: ActiveType): List<DotoriEvent>

    @Query("""
    {
      'createdYear': ?0, 'createdMonth': ?1, 'createdDay': ?2, 
      'eventType': 'MASSAGE'
    }
    """, sort = "{ 'createdAt': 1 }")
    fun findMassageLogsWithoutActiveType(year: Int, month: Int, day: Int): List<DotoriEvent>
}