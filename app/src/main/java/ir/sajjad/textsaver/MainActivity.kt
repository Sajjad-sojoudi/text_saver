package ir.sajjad.textsaver

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import ir.sajjad.textsaver.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            val navController = findNavController(R.id.fragmentContainerView) // R.id.nav_host_fragment باید با ID فرگمنت میزبان شما جایگزین شود

            when (item.itemId) {
                R.id.item_home -> {
                    navController.navigate(R.id.homeFragment)
                }
                R.id.item_register -> {
                    navController.navigate(R.id.registerFragment)
                }
                R.id.item_profile -> {
                    navController.navigate(R.id.profileFragment)
                }
            }

            true
        }




    }
}