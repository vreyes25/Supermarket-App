package com.example.supermarketapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.supermarketapp.models.Categories
import com.squareup.picasso.Picasso

interface CategoryClickListener{
    fun categoryClicked(category: Categories)
}

class CategoriesRecyclerViewAdapter(private val categories: ArrayList<Categories>, private val listener: CategoryClickListener) : RecyclerView.Adapter<CategoriesViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.categories_view, parent, false)
        return CategoriesViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        val category = categories[position]
        holder.codeCategory.text = category?.categoryId.toString()
        holder.categoryName.text = category?.categoryName
        Picasso.get().load(category.categoryPhoto).into(holder.categoryPhoto)
        holder.itemView.setOnClickListener {
            listener.categoryClicked(category)
        }
    }

    override fun getItemCount(): Int {
        return categories.count()
    }

}
