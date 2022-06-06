package com.anastasiyayuragina.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class RecipeModel(
    val id: Int,
    val name: String,
    val image: String? = null,
    val description: String? = null,
    val recipeUrl: String? = null,
    val ingredientModels: List<IngredientModel>,
    val wasCooked: Boolean = false
)

