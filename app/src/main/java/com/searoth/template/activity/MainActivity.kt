package com.searoth.template.activity

import android.net.Uri
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.google.gson.GsonBuilder
import com.searoth.template.R
import com.searoth.template.fragment.HomeFragment
import com.searoth.template.fragment.SecondFragment
import com.searoth.template.fragment.ThirdFragment
import com.searoth.template.other.Models
import com.searoth.template.other.MyToast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
            HomeFragment.OnFragmentInteractionListener,
            SecondFragment.OnFragmentInteractionListener,
            ThirdFragment.OnFragmentInteractionListener{

    var localFeed: Models.LocalFeed? = null

    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun fetchJSON(){
        println("Attempting to Fetch JSON")

        val url = "http://www.gamermessenger.com/savorlyapp/food_feed.json"

        val request = Request.Builder().url(url).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call?, response: Response?) {
                val body = response?.body()?.string()
                //println(body)
                val gson = GsonBuilder().create()
                val listOfFoodForUser =  gson.fromJson(body, Models.LocalFeed::class.java)
                localFeed = listOfFoodForUser
                fragmentTransaction(HomeFragment())
            }

            override fun onFailure(call: Call?, e: IOException?) {
                println("Failed to execute request")
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
        fetchJSON()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun loadFragment(menuId: Int) {
        var fragment: Fragment = HomeFragment()
        when (menuId) {
            R.id.nav_home -> fragment = HomeFragment()
            R.id.nav_gallery -> fragment = SecondFragment()
            R.id.nav_slideshow -> fragment = ThirdFragment()
        }
        fragmentTransaction(fragment)
    }

    // This method will take a fragment and add/replace
    // that fragment to the activity
    private fun fragmentTransaction(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.main_frame, fragment)
                .commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (item.itemId) {
            R.id.nav_home -> {
                title = "Home"
            }
            R.id.nav_gallery -> {
                title = "gallery"
            }
            R.id.nav_slideshow -> {
                title = "slideshow"
            }
            R.id.nav_manage -> {
                title = "manage"
                MyToast.showLong(this, "Fragment not setup")
            }
            R.id.nav_share -> {
                title = "share"
                MyToast.showLong(this, "Fragment not setup")
            }
            R.id.nav_send -> {
                title = "send"
                MyToast.showLong(this, "Fragment not setup")
            }
        }
        loadFragment(id)
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
