package app.mobify.orderbread.feature.api.models

import java.math.BigDecimal



data class BreadItem(
    val allergic: String,
    val combinations: String,
    val currency: String,
    val description: String,
    val dimensions: String,
    val durability: String,
    val flavor: String,
    val id: Int,
    val images: List<String>,
    val name: String,
    val nutritional: String,
    val price: BigDecimal,
    val thumbnail: String
)