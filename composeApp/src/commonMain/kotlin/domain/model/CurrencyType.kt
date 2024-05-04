package domain.model


//
// Created by Code For Android on 04/05/24.
// Copyright (c) 2024 CFA. All rights reserved.
//

sealed class CurrencyType(
    val code: CurrencyCode
) {
    data class Source(val currencyCode: CurrencyCode) : CurrencyType(currencyCode)
    data class Target(val currencyCode: CurrencyCode) : CurrencyType(currencyCode)

    data object None : CurrencyType(CurrencyCode.USD)

}