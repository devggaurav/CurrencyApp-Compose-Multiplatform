package domain

interface PreferenceRepository {
    suspend  fun saveLastUpdated(lastUpdated : String)
     suspend fun isDateFresh(currentTimeStamp : Long) : Boolean

}