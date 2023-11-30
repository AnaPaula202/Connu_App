package com.example.connu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.viewpager2.widget.ViewPager2
import com.example.connu.Adapter.FragmentsAdapter
import com.example.connu.Fragments.MYPageFragment
import com.example.connu.Fragments.MainPageFragment
import com.example.connu.Fragments.ProfileFragment
import com.example.connu.databinding.ActivityMainBinding
import com.example.connu.databinding.ActivityMainPageBinding
import com.google.android.material.tabs.TabLayout

class MainPageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bLogout : ImageView = findViewById(R.id.ivLogout)

        bLogout.setOnClickListener {
            val logout = Intent(this, MainActivity::class.java)
            startActivity(logout)
        }

        binding.tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        val adapter = FragmentsAdapter(this, supportFragmentManager, binding.tabLayout.tabCount)
        binding.vpContent.adapter = adapter
        binding.vpContent.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout))
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.vpContent.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })


    }



}
