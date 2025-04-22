package com.teymoorianar.anarbottombarwithfab

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.teymoorianar.anarfabbottombar.AnarMenuItem

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // initiating anar bottom bar
        val anarBottomBar: com.teymoorianar.anarfabbottombar.AnarBottomBarWithFab
            = findViewById(R.id.anarbottombar)

        anarBottomBar.addMenuItem(object : AnarMenuItem {
            override var iconResourceId: Int = R.drawable.icons8_home_64
            override var title: String = "خانه"

            override fun callOnClick() {
//                navController.navigate(R.id.homeFragment)
            }

        })
        anarBottomBar.addMenuItem(object : AnarMenuItem {
            override var iconResourceId: Int = R.drawable.icons8_settings_48
            override var title: String = "تنظیمات"

            override fun callOnClick() {
//                navController.navigate(R.id.settingsFragment)
            }

        })
    }
}