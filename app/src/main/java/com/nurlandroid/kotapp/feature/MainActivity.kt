package com.nurlandroid.kotapp.feature

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.nurlandroid.kotapp.R
import com.nurlandroid.kotapp.common.CommonViewModel
import com.nurlandroid.kotapp.common.extension.setupWithNavController
import com.nurlandroid.kotapp.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewBinding: ActivityMainBinding by viewBinding(R.id.container)
    private val commonViewModel: CommonViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            initBottomNavigation()
        }

        commonViewModel.actionLiveData.observe(this, {
            when (it) {
                is CommonViewModel.Action.MoveToMain -> moveToMain()
                else -> {
                }
            }
        })
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        initBottomNavigation()
    }

    private fun initBottomNavigation() {

        val navGraphIds = listOf(
                R.navigation.main_nav
        )

        val controller = viewBinding.bottomNavigation.setupWithNavController(
                navGraphIds = navGraphIds,
                fragmentManager = supportFragmentManager,
                containerId = R.id.fragmentContainer,
                intent = intent
        )

        controller.observe(this, Observer(::onControllerChanged))
    }

    private fun onControllerChanged(data: NavController) {
        data.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.postFragment -> viewBinding.bottomNavigation.isVisible = true
                else -> viewBinding.bottomNavigation.isVisible = true
            }
        }
    }

    private fun moveToMain() {
        viewBinding.bottomNavigation.selectedItemId = viewBinding.bottomNavigation.menu[0].itemId
    }
}