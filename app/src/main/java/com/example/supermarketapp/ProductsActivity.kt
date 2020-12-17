package com.example.supermarketapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.supermarketapp.models.Categories
import com.example.supermarketapp.models.Products
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_products.*

class ProductsActivity : AppCompatActivity() {
    private lateinit var category: Categories
    private lateinit var db: FirebaseDatabase
    private lateinit var productsRef: DatabaseReference
    private val tag = "Supermarket"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)
        category = intent.getParcelableExtra<Categories>("Category")!!

        db = FirebaseDatabase.getInstance()
        productsRef = db.getReference("Products")
        Log.d(tag, productsRef.toString())

        productsRecyclerView.layoutManager = LinearLayoutManager(this)
        productsRecyclerView.setHasFixedSize(true)
        val adapter = ProductRecyclerViewAdapter(ArrayList())
        productsRecyclerView.adapter = adapter
        productListener()
    }

    private fun productListener() {
        Log.d(tag, category.toString())
        var categoryId = 0
        if(category.categoryId != null){
            categoryId = category.categoryId!!
        }
        val query = productsRef.orderByChild("categoryId").equalTo(categoryId.toDouble())

        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.w(tag, snapshot.toString())
                val data = snapshot.children
                data?.let {
                    val productData = ArrayList<Products>()
                    Log.w(tag, data.toString())
                    for (dat in data) {
                        val product = dat.getValue(Products::class.java)

                        product?.let {
                            productData.add(product)
                        }
                    }
                    val adapter = ProductRecyclerViewAdapter(productData)
                    productsRecyclerView.adapter = adapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(tag, "Can't connect with the products database", error.toException())
                Toast.makeText(baseContext, "Failure trying to load products", Toast.LENGTH_LONG)
            }
        }
        query.addValueEventListener(listener)
    }
}