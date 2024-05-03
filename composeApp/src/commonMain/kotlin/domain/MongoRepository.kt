package domain

import domain.model.Currency
import domain.model.RequestState
import kotlinx.coroutines.flow.Flow


//
// Created by Code For Android on 03/05/24.
// Copyright (c) 2024 CFA. All rights reserved.
//

interface MongoRepository {

    fun configureTheRealm()
    suspend fun insertCurrencyData(currency: Currency)
    fun readCurrencyData() : Flow<RequestState<List<Currency>>>
}