package data.remote.api

import domain.CurrencyApiService

class CurrencyApiServiceImpl : CurrencyApiService {

    companion object{
        const val ENDPOINT = "https://api.currencyapi.com/v3/latest"
        const val API_KEY = "cur_live_LuYRh5D9nzi7MMTDrYSISMWqPiJEIbvwWa2hlCU9"
    }
}