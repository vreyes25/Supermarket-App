package com.example.supermarketapp.models

import android.os.Parcelable
import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties
import kotlinx.android.parcel.Parcelize

@Parcelize
@IgnoreExtraProperties
data class Categories (
    var categoryId: Int? = 0,
    var categoryName: String? = "",
    var categoryPhoto: String? = "",
) : Parcelable {

    @Exclude
    fun toMap():Map<String, Any?>{
        return mapOf(
            "categoryId" to categoryId,
            "categoryName" to categoryName,
            "categoryPhoto" to categoryPhoto
        )
    }
}