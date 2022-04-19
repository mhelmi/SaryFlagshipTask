package com.github.mhelmi.saryflagship.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.github.mhelmi.saryflagship.R
import com.github.mhelmi.saryflagship.core.extesions.toast
import com.github.mhelmi.saryflagship.databinding.ActivityMainBinding
import com.github.mhelmi.saryflagship.databinding.LayoutShoppingCartActionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

  private lateinit var appBarConfiguration: AppBarConfiguration
  private lateinit var binding: ActivityMainBinding

  private val topLevelFragments = setOf(
    R.id.storeFragment,
    R.id.ordersFragment,
    R.id.profileFragment,
  )

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    setSupportActionBar(binding.toolbar)

    val navController = findNavController(R.id.nav_host_fragment_content_main)
    appBarConfiguration = AppBarConfiguration(topLevelFragments)
    setupActionBarWithNavController(navController, appBarConfiguration)
    binding.contentMain.navView.setupWithNavController(navController)
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    menuInflater.inflate(R.menu.menu_main, menu)
    return true
  }

  override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
    val cartItem = menu?.findItem(R.id.action_shopping_cart)
    cartItem?.let {
      LayoutShoppingCartActionBinding.bind(cartItem.actionView).apply {
        tvBadgeCounter.text = "5"
        tvBadgeCounter.isVisible = tvBadgeCounter.text?.isBlank() == false
        root.setOnClickListener { onOptionsItemSelected(cartItem) }
      }
    }
    return super.onPrepareOptionsMenu(menu)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    return when (item.itemId) {
      R.id.action_shopping_cart -> {
        toast("Open shopping cart")
        true
      }
      else -> super.onOptionsItemSelected(item)
    }
  }

  override fun onSupportNavigateUp(): Boolean {
    val navController = findNavController(R.id.nav_host_fragment_content_main)
    return navController.navigateUp(appBarConfiguration)
      || super.onSupportNavigateUp()
  }
}