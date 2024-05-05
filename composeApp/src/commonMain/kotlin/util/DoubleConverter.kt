package util

import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.TwoWayConverter


//
// Created by Code For Android on 05/05/24.
// Copyright (c) 2024 CFA. All rights reserved.
//

class DoubleConverter : TwoWayConverter<Double, AnimationVector1D> {
    override val convertFromVector: (AnimationVector1D) -> Double = { vector ->
        vector.value.toDouble()
    }

    override val convertToVector: (Double) -> AnimationVector1D = { value ->
        AnimationVector1D(value.toFloat())
    }

}