package com.example.mvp_mvvm.ui

import com.example.mvp_mvvm.databinding.ActivityMainBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.mvp_mvvm.R
import com.example.mvp_mvvm.ui.authorization.AuthorizationFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            setHomePage()
        }
    }

    private fun navigationTo(f: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container, f).commit()
    }

    private fun setHomePage(){
        navigationTo(AuthorizationFragment.newInstance())
    }
}