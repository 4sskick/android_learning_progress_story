package id.niteroomcreation.learningprogressstory.presenter.feature.main

import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import id.niteroomcreation.learningprogressstory.R
import id.niteroomcreation.learningprogressstory.databinding.AMainBinding
import id.niteroomcreation.learningprogressstory.presenter.base.BaseActivity

/**
 * Created by Septian Adi Wijaya on 24/01/2023.
 * please be sure to add credential if you use people's code
 */
class MainActivity : BaseActivity() {

    companion object {
        val TAG = MainActivity::class.java.simpleName
    }

    private lateinit var binding: AMainBinding
    private lateinit var fragmentManager: FragmentManager

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreateInside() {
        binding = AMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fr_main) as NavHostFragment
//        val navController:NavController = Navigation.findNavController(this, R.id.fr_main)
        val navController = navHostFragment.navController

//        val navController = Navigation.findNavController(this, R.id.fr_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(navController.graph)

        setupActionBarWithNavController(navController, appBarConfiguration)
//        setupActionBarWithNavController(navController)



        binding.mainNavBottom.setupWithNavController(navController)
//        NavigationUI.setupWithNavController(binding.mainNavBottom, navController)
    }

    override fun contentLayout(): Int {
        return R.layout.a_main
    }

    override fun initUI() {
        fragmentManager = supportFragmentManager
    }
}