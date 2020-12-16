package com.example.supermarketapp

import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

interface MenuListener{
    fun menuOptionClicked(item: SupermarketModel, position: Int)
}

class SupermarketAdapter(
        private val context : Context,
        private val images : List <SupermarketModel>
) : RecyclerView.Adapter<SupermarketAdapter.ImageViewHolder>(){

    class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val img = itemView.findViewById<ImageView>(R.id.image)
        private val imgTitle = itemView.findViewById<TextView>(R.id.titleTextView)
        fun bindView(image: SupermarketModel) {
            img.setImageResource(image.imageSrc)
            imgTitle.text = image.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder =
            ImageViewHolder(LayoutInflater.from(context).inflate(R.layout.supermarket_menu, parent, false))

    override fun getItemCount(): Int = images.size

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bindView(images[position])
    }
}