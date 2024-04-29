package data.remote.api

import domain.CurrencyApiService
import domain.model.ApiResponse
import domain.model.Currency
import domain.model.RequestState
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class CurrencyApiServiceImpl : CurrencyApiService {

    companion object {
        const val ENDPOINT = "https://api.currencyapi.com/v3/latest"
        const val API_KEY = "cur_live_LuYRh5D9nzi7MMTDrYSISMWqPiJEIbvwWa2hlCU9"
    }

    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
        install(HttpTimeout) {
            requestTimeoutMillis = 15000
        }
        install(DefaultRequest) {
            headers {
                append("apiKey", API_KEY)
            }
        }
    }

    override suspend fun getLatestExchangeRates(): RequestState<List<Currency>> {
        return try {
            val response = httpClient.get(ENDPOINT)
            if (response.status.value == 200) {
                println("Api response: ${response.body<String>()}")
                val apiResponse = Json.decodeFromString<ApiResponse>(response.body())
                RequestState.Success(data = apiResponse.data.values.toList())
            } else {
                RequestState.Error(message = "Http Error code: ${response.status}")
            }

        } catch (e: Exception) {
            RequestState.Error(message = e.message.toString())
        }
    }
}