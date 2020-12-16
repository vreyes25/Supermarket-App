package com.example.supermarketapp.models

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Products (
    var productId: Int? = 0,
    var name: String? = "",
    var price: String? = "",
    var productPhoto: String? = "",
    var categoryId: Int? = 0
) {
    @Exclude
    fun toMap():Map<String, Any?>{
        return mapOf(
            "productId" to productId,
            "name" to name,
            "price" to price,
            "productPhoto" to productPhoto,
            "categoryId" to categoryId
        )
    }
}