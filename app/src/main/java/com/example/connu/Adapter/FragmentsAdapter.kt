package com.example.connu.Adapter

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.connu.ForgottenPassActivity
import com.example.connu.Fragments.LogoutFragment
import com.example.connu.Fragments.MYPageFragment
import com.example.connu.Fragments.MainPageFragment
import com.example.connu.Fragments.ProfileFragment

internal class FragmentsAdapter (val context: Context, val fragmentManager: FragmentManager, val totalTabs : Int) :
    FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {

        return when(position){
            0 -> {
                MainPageFragment()
            }
            1 -> {
                MYPageFragment()
            }
            2 -> {
                ProfileFragment()
            }
            3 -> {
                LogoutFragment()
            }
            else -> getItem(position)
        }
    }
    override fun getCount(): Int {

        return totalTabs
    }


}