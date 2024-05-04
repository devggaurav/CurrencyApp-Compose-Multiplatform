package presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign


//
// Created by Code For Android on 04/05/24.
// Copyright (c) 2024 CFA. All rights reserved.
//

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    message: String? = null
) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message ?: "No Data..",
            textAlign = TextAlign.Center
        )


    }


}