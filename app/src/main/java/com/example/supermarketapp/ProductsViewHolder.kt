package com.example.supermarketapp

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val productName = itemView.findViewById<TextView>(R.id.productName)
    val productPrice = itemView.findViewById<TextView>(R.id.productPrice)
    val productPhoto = itemView.findViewById<ImageView>(R.id.productImage)
}
