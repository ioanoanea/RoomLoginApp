package com.example.roomloginapp.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.roomloginapp.ui.fragments.HomeFragment
import com.example.roomloginapp.ui.fragments.NotificationsFragment

class MainViewPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) {
            HomeFragment()
        } else {
            NotificationsFragment()
        }
    }

}