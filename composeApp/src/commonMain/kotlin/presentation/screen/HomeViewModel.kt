package presentation.screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import domain.CurrencyApiService
import domain.PreferenceRepository
import domain.model.Currency
import domain.model.RateStatus
import domain.model.RequestState
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock


//
// Created by Code For Android on 02/05/24.
// Copyright (c) 2024 CFA. All rights reserved.
//
sealed class HomeUiEvent {
    data object RefreshRates : HomeUiEvent()
}

class HomeViewModel(
    private val preferenceRepository: PreferenceRepository,
    private val api: CurrencyApiService
) : ScreenModel {

    private var _rateStatus: MutableState<RateStatus> = mutableStateOf(RateStatus.Idle)
    val rateStatus: State<RateStatus> = _rateStatus


    private val _sourceCurrency : MutableState<RequestState<Currency>> = mutableStateOf(RequestState.Idle)
    val sourceCurrency : State<RequestState<Currency>> = _sourceCurrency


    private val _targetCurrency : MutableState<RequestState<Currency>> = mutableStateOf(RequestState.Idle)
    val targetCurrency : State<RequestState<Currency>> = _targetCurrency

    init {
        screenModelScope.launch {
            fetchNewRates()
           // getRatesStatus()
        }

    }

    fun sendEvent(event: HomeUiEvent) {
        when (event) {
            HomeUiEvent.RefreshRates -> {
                screenModelScope.launch {
                    fetchNewRates()
                }
            }


            else -> {}
        }

    }

    private suspend fun fetchNewRates() {
        try {
            api.getLatestExchangeRates()
            getRatesStatus()
        } catch (e: Exception) {
            println(e.message)
        }
    }

    private suspend fun getRatesStatus() {
        _rateStatus.value = if (preferenceRepository.isDateFresh(
                currentTimeStamp = Clock.System.now().toEpochMilliseconds()
            )
        ) RateStatus.FRESH
        else RateStatus.Stale
    }

}