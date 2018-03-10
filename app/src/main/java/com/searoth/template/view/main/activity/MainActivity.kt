package com.searoth.template.view.main.activity

import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.searoth.template.R
import com.searoth.template.databinding.ActivityMainBinding
import com.searoth.template.model.LocalFeed
import com.searoth.template.util.Toast
import com.searoth.template.viewmodel.main.activity.MainActivityViewModel
import com.searoth.template.viewmodel.main.activity.OnSuccess
import com.searoth.template.view.main.fragment.HomeFragment
import com.searoth.template.view.main.fragment.SecondFragment
import com.searoth.template.view.main.fragment.ThirdFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
        HomeFragment.OnFragmentInteractionListener,
        SecondFragment.OnFragmentInteractionListener,
        ThirdFragment.OnFragmentInteractionListener, OnSuccess {


    private var binding: ActivityMainBinding? = null
    var viewModel: MainActivityViewModel? = null

    var localFeed: LocalFeed? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = MainActivityViewModel(this, this)
        binding?.viewmodel = viewModel

        setUpDrawer()

        nav_view.setNavigationItemSelectedListener(this)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
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
                Toast.showLong(this, "Fragment not setup")
            }
            R.id.nav_share -> {
                title = "share"
                Toast.showLong(this, "Fragment not setup")
            }
            R.id.nav_send -> {
                title = "send"
                Toast.showLong(this, "Fragment not setup")
            }
        }
        loadFragment(id)
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onSuccessLoad(list: LocalFeed) {
        localFeed = list
        fragmentTransaction(HomeFragment())
    }

    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun setUpDrawer() {
        val toggle =
                ActionBarDrawerToggle(this, drawer_layout,
                        toolbar,
                        R.string.navigation_drawer_open,
                        R.string.navigation_drawer_close)

        drawer_layout
                .addDrawerListener(toggle)

        toggle.syncState()
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
}
