package com.example.supermarketapp

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CategoriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val codeCategory = itemView.findViewById<TextView>(R.id.idCategoryTV)
    val categoryName = itemView.findViewById<TextView>(R.id.categoryTextView)
    val categoryPhoto = itemView.findViewById<ImageView>(R.id.categoryImage)
}
