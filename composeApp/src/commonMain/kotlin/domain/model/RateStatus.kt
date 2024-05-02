package domain.model

import androidx.compose.ui.graphics.Color
import ui.theme.freshColor
import ui.theme.staleColor


//
// Created by Code For Android on 02/05/24.
// Copyright (c) 2024 CFA. All rights reserved.
//

enum class RateStatus(
    val title: String,
    val color: Color
) {
    Idle(
        title = "Rates",
        color = Color.White

    ),
    FRESH(
        title = "Fresh Rates",
        color = freshColor
    ),
    Stale(
        title = "Rates are not fresh",
        color = staleColor
    )
}