package com.nurlandroid.kotapp.feature

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nurlandroid.kotapp.R
import com.nurlandroid.kotapp.common.CustomFragmentFactory
import com.nurlandroid.kotapp.feature.posts.PostFragment
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val customFragmentFactory: CustomFragmentFactory by inject()

    private val navigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.menu_item_1 -> {
                showFragment(PostFragment())
                return@OnNavigationItemSelectedListener true
            }

            R.id.menu_item_2 -> {
                showFragment(PostFragment())
                return@OnNavigationItemSelectedListener true
            }

            R.id.menu_item_3 -> {
                showFragment(PostFragment())
                return@OnNavigationItemSelectedListener true
            }
        }

        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.fragmentFactory = customFragmentFactory
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener)
        bottomNavigationView.menu.getItem(0).isChecked = true
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out)
                .replace(R.id.fragmentContainer, fragment, fragment.javaClass.simpleName)
                .commit()
    }
}