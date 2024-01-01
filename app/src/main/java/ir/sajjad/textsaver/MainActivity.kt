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
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            val navController = findNavController(R.id.fragmentContainerView)

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