package domain

import domain.model.Currency
import domain.model.CurrencyCode
import kotlinx.coroutines.flow.Flow

interface PreferenceRepository {
    suspend fun saveLastUpdated(lastUpdated: String)
    suspend fun isDateFresh(currentTimeStamp: Long): Boolean

    suspend fun saveSourceCurrencyCode(code: String)
    suspend fun saveTargetCurrencyCode(code: String)

    fun readSourceCurrencyCode(): Flow<CurrencyCode>
    fun readTargetCurrencyCode(): Flow<CurrencyCode>

}