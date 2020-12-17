package com.example.supermarketapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.supermarket_menu.*

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, MenuListener {
    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, 0, 0
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)

        laColoniaCV.setOnClickListener {
            val showCategories = Intent(this, CategoriesActivity::class.java)
            startActivity(showCategories)
        }


        val images = listOf<SupermarketModel>(
                SupermarketModel("LA COLONIA", R.drawable.lacolonia_logo),
                SupermarketModel("PAIZ", R.drawable.paiz_logo),
                SupermarketModel("WALMART", R.drawable.walmart_logo),
                SupermarketModel("MAXI DESPENSA", R.drawable.maxi_despensa_logo),
        )

        /*val recyclerView = findViewById<RecyclerView>(R.id.menuRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = SupermarketAdapter(this, images)*/
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> {
                val backHome = Intent(this, CategoriesActivity::class.java)
                startActivity(backHome)
            }
            R.id.nav_shopping -> {
                Toast.makeText(this, "Shopping Cart clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_favourites -> {
                Toast.makeText(this, "Favourites clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_profile -> {
                Toast.makeText(this, "Profile clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_logout -> {
                FirebaseAuth.getInstance().signOut()
                val authReturn = Intent(this, AuthActivity::class.java)
                startActivity(authReturn)
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun menuOptionClicked(item: SupermarketModel, position: Int) {

    }
}