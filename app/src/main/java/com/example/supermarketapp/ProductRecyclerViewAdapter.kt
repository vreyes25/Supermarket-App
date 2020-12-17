package com.example.supermarketapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.supermarketapp.models.Products
import com.squareup.picasso.Picasso

class ProductRecyclerViewAdapter(var products: ArrayList<Products>) : RecyclerView.Adapter<ProductsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.products_view,parent,false)
        return ProductsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val product = products[position]
        holder.productName.text = product.name
        holder.productPrice.text = product.price
        Picasso.get().load(product.productPhoto).into(holder.productPhoto)
    }

    override fun getItemCount(): Int {
        return products.count()
    }
}