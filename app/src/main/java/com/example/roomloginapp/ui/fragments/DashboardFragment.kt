package com.example.roomloginapp.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.roomloginapp.R
import com.example.roomloginapp.ui.adapters.CurrenciesRecyclerViewAdapter
import com.example.roomloginapp.ui.adapters.DashboardViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragrmet_dashboard.*
import kotlinx.android.synthetic.main.your_dashboard_card.*

class DashboardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragrmet_dashboard, container, false)
    }

    @SuppressLint("SetTextI18n")
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

        // Set recyclerView items
        val items: Array<CurrenciesRecyclerViewAdapter.Item> = arrayOf(
            CurrenciesRecyclerViewAdapter.Item(
                "Bitcoin",
                "BTC",
                "49.23%",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9a/BTC_Logo.svg/1200px-BTC_Logo.svg.png"
            ),
            CurrenciesRecyclerViewAdapter.Item(
                "Ethereum",
                "ETH",
                "49.23%",
                "https://thumbs.dreamstime.com/b/simple-ethereum-logo-isolated-logo-business-finance-simple-ethereum-logo-113645065.jpg",
                true
            ),
            CurrenciesRecyclerViewAdapter.Item(
                "Bitcoin",
                "BTC",
                "49.23%",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9a/BTC_Logo.svg/1200px-BTC_Logo.svg.png"
            ),
            CurrenciesRecyclerViewAdapter.Item(
                "Ethereum",
                "ETH",
                "49.23%",
                "https://thumbs.dreamstime.com/b/simple-ethereum-logo-isolated-logo-business-finance-simple-ethereum-logo-113645065.jpg"
            ),
            CurrenciesRecyclerViewAdapter.Item(
                "Bitcoin",
                "BTC",
                "49.23%",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9a/BTC_Logo.svg/1200px-BTC_Logo.svg.png",
                true
            ),
            CurrenciesRecyclerViewAdapter.Item(
                "Ethereum",
                "ETH",
                "49.23%",
                "https://thumbs.dreamstime.com/b/simple-ethereum-logo-isolated-logo-business-finance-simple-ethereum-logo-113645065.jpg"
            ),
            CurrenciesRecyclerViewAdapter.Item(
                "Bitcoin",
                "BTC",
                "49.23%",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9a/BTC_Logo.svg/1200px-BTC_Logo.svg.png"
            ),
            CurrenciesRecyclerViewAdapter.Item(
                "Ethereum",
                "ETH",
                "49.23%",
                "https://thumbs.dreamstime.com/b/simple-ethereum-logo-isolated-logo-business-finance-simple-ethereum-logo-113645065.jpg"
            )
        )

        // Set recyclerView
        val customAdapter = CurrenciesRecyclerViewAdapter(items)
        recyclerView.layoutManager = GridLayoutManager(context, 2, GridLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = customAdapter
    }
}