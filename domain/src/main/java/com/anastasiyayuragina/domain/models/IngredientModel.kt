package com.anastasiyayuragina.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class IngredientModel(
    val id: Int,
    val name: String,
    val amount: Float,
    val amountType: IngredientAmountTypeModel
)
