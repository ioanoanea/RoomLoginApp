package com.example.roomloginapp.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.roomloginapp.R
import com.example.roomloginapp.ui.adapters.DashboardViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.your_dashboard_card.*

class DashboardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager.adapter = DashboardViewPagerAdapter(context as FragmentActivity)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Buy"
                1 -> tab.text = "View"
                2 -> tab.text = "Manage"
            }
        }.attach()

        for (i in 0 until tabLayout.tabCount) {
            val tab = (tabLayout.getChildAt(0) as ViewGroup).getChildAt(i)
            val p = tab.layoutParams as ViewGroup.MarginLayoutParams
            p.setMargins(16, 0, 16, 0)
            tab.requestLayout()
        }

        /*
        // Get initial top layout params
        val dashboardCardLayoutParams = dashboardCardLayout.layoutParams

        // Expand
        expandButton.setOnClickListener {
            val heightDifference = recyclerView.height / 2 - 50
            // Contract recyclerView
            recyclerView.layoutManager = GridLayoutManager(context, 1, GridLayoutManager.HORIZONTAL, false)
            recyclerView.adapter?.apply {
                notifyDataSetChanged()
            }
            // Increase top layout height
            dashboardCardLayout.layoutParams = FrameLayout.LayoutParams(dashboardCardLayoutParams.width, dashboardCardLayout.height + heightDifference)

            // Show contract button
            expandButton.visibility = View.INVISIBLE
            contractButton.visibility = View.VISIBLE
        }

        // Contract
        contractButton.setOnClickListener {
            // Set top layout initial params
            dashboardCardLayout.layoutParams = dashboardCardLayoutParams
            // Reset recyclerView
            recyclerView.layoutManager = GridLayoutManager(context, 2, GridLayoutManager.HORIZONTAL, false)
            recyclerView.adapter?.apply {
                notifyDataSetChanged()
            }

            // Show expand button
            contractButton.visibility = View.INVISIBLE
            expandButton.visibility = View.VISIBLE
        }
         */
    }
}