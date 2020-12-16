package com.example.supermarketapp.models

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Categories (
    var categoryId: Int? = 0,
    var categoryName: String? = "",
    var categoryPhoto: String? = "",
) {
    @Exclude
    fun toMap():Map<String, Any?>{
        return mapOf(
            "categoryId" to categoryId,
            "categoryName" to categoryName,
            "categoryPhoto" to categoryPhoto
        )
    }
}