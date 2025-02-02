package com.example.supermarketapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.supermarketapp.models.Categories
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_categories.*

class CategoriesActivity : AppCompatActivity(), CategoryClickListener {
    private lateinit var db: FirebaseDatabase
    private lateinit var categoriesReference: DatabaseReference

    val tag = "Supermarket"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)

        db = FirebaseDatabase.getInstance()
        categoriesReference = db.getReference("Categories")
        categoryListener()
        val adapter = CategoriesRecyclerViewAdapter(ArrayList(), this)
        categoriesRecyclerView.layoutManager = GridLayoutManager(this, 2)
        categoriesRecyclerView.adapter = adapter
    }

    private fun categoryListener() {
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.w(tag, snapshot.toString())
                val data = snapshot.children
                data.let {
                    Log.w(tag, data.toString())
                    val autDat = ArrayList<Categories>()
                    for (dat in data) {
                        val category = dat.getValue(Categories::class.java)
                        category?.let {
                            autDat.add(category)
                        }
                    }
                    val adapter = CategoriesRecyclerViewAdapter(autDat, this@CategoriesActivity)
                    categoriesRecyclerView.adapter = adapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(
                    tag,
                    "Can't connect with the products database",
                    error.toException()
                )
                Toast.makeText(baseContext, "Failure trying to load categories", Toast.LENGTH_LONG).show()
            }
        }
        categoriesReference.addValueEventListener(listener)
    }

    override fun categoryClicked(category: Categories) {
        val intent = Intent(this, ProductsActivity::class.java)
        intent.putExtra("Category", category)
        startActivity(intent)
    }

    fun showMenu(view: View) {
        val backToMenu = Intent(this, HomeActivity::class.java)
        startActivity(backToMenu)
    }
}