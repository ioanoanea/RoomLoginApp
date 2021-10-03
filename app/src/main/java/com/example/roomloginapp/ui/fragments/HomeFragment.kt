package com.example.roomloginapp.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.roomloginapp.R
import com.example.roomloginapp.ui.adapters.CurrenciesRecyclerViewAdapter
import com.example.roomloginapp.ui.adapters.HomeViewPagerAdapter
import com.example.roomloginapp.viewmodel.UserViewModel
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragrmet_dashboard.*

class HomeFragment : Fragment() {

    private val userViewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager.adapter = HomeViewPagerAdapter(context as FragmentActivity)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when(position) {
                0 -> tab.text = "Overview"
                1 -> tab.text = "Tasks"
                2 -> tab.text = "Trades"
                3 -> tab.text = "Label"
            }
        }.attach()

    }
}