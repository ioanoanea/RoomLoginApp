package com.example.roomloginapp.ui.fragments

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import com.example.roomloginapp.R
import com.example.roomloginapp.ui.adapters.MainViewPagerAdapter
import com.example.roomloginapp.viewmodel.UserViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_home.*

class LoggedInFragment : Fragment() {

    private val userViewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_logged_in, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager.adapter = MainViewPagerAdapter(context as FragmentActivity)

        // View pager and tab layout
        TabLayoutMediator(tabLayout, viewPager) { _, _ ->
        }.attach()

        // Set tabLayout tab icons
        tabLayout.getTabAt(0)!!.setIcon(R.drawable.ic_home)
        tabLayout.getTabAt(1)!!.setIcon(R.drawable.ic_notifications)

        // TabLayout on tab selected listener
        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            @RequiresApi(Build.VERSION_CODES.M)
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.icon?.setTint(resources.getColor(R.color.blue))
            }

            @RequiresApi(Build.VERSION_CODES.M)
            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab?.icon?.setTint(resources.getColor(R.color.dark_grey))
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                tab?.icon?.setTint(resources.getColor(R.color.blue))
            }

        })

        // select home tab
        tabLayout.getTabAt(0)!!.select()

        viewPager.isUserInputEnabled = false
    }

}