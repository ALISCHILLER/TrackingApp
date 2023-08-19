package com.msa.trackingapp.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.msa.trackingapp.R
import com.msa.trackingapp.databinding.ActivityMainBinding
import com.msa.trackingapp.util.Constants.Companion.ACTION_SHOW_TRACKING_ACTIVITY
import com.msa.trackingapp.util.Constants.Companion.ACTION_SHOW_TRACKING_FRAGMENT
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var name: String

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHost) as NavHostFragment
        val navController = navHostFragment.navController

        setSupportActionBar(binding.toolbar)
        binding.bottomNavigationView.setupWithNavController(navController)
        binding.bottomNavigationView.setOnNavigationItemReselectedListener { /* NO-OP */ }

//        navigateToTrackingFragmentIfNeeded(intent)

        if(name.isNotEmpty()) {
            val toolbarTitle = "Let's go, $name!"
            binding.tvToolbarTitle?.text = toolbarTitle
        }

//        binding.navHost.findNavController()
//            .addOnDestinationChangedListener { _, destination, _ ->
//                when (destination.id) {
//                    R.id.setupFragment, R.id.trackingFragment -> binding.bottomNavigationView.visibility =
//                        View.GONE
//                    else -> binding.bottomNavigationView.visibility = View.VISIBLE
//                }
//            }
    }


    //Checks if we launched the activity from the notification
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
    }

    private fun navigateToTrackingFragmentIfNeeded(intent: Intent?) {
        if(intent?.action == ACTION_SHOW_TRACKING_ACTIVITY) {
           binding.navHost.findNavController().navigate(R.id.action_global_trackingFragment)
        }
    }
}