package app.mobify.orderbread.feature.api.models

import java.math.BigDecimal

data class BreadItem(
        val id: Int,
        val name: String,
        val thumbnail: String,
        val images: List<String>,
        val price: BigDecimal,
        val dimensions: String,
        val description: String,
        val combinations: String,
        val flavor: String,
        val durability: String,
        val ingredients: String,
        val allergic: String,
        val nutritional: String,
        val currency: String
)