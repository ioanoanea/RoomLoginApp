package com.example.roomloginapp.ui.fragments

import android.annotation.SuppressLint
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
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


class HomeFragment : Fragment() {

    private val userViewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
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

        val root = tabLayout.getChildAt(0)
        if (root is LinearLayout) {
            (root as LinearLayout).showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
            val drawable = GradientDrawable()
            drawable.setColor(resources.getColor(com.example.roomloginapp.R.color.grey))
            drawable.setSize(2, 1)
            (root as LinearLayout).dividerPadding = 30
            (root as LinearLayout).dividerDrawable = drawable
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
                "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9a/BTC_Logo.svg/1200px-BTC_Logo.svg.png",
                true
            ),
        )

        // Set recyclerView
        val customAdapter = CurrenciesRecyclerViewAdapter(items)
        recyclerView.layoutManager = GridLayoutManager(context, 2, GridLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = customAdapter

        // Get initial bottom Layout layout params
        val bottomLayoutParams: ViewGroup.LayoutParams = bottomLayout.layoutParams

        buttonViewAll.setOnClickListener {
            // Set Currencies bottom layout to fo fullscreen mode
            bottomLayout.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            // Change recycler view orientation to vertical
            recyclerView.layoutManager = GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
            recyclerView.adapter?.apply {
                notifyDataSetChanged()
            }
            // Show close button
            buttonViewAll.visibility = View.INVISIBLE
            buttonClose.visibility = View.VISIBLE
        }

        buttonClose.setOnClickListener {
            // Set Currencies bottom layout to initial mode
            bottomLayout.layoutParams = bottomLayoutParams
            // Change recycler view orientation to horizontal
            recyclerView.layoutManager = GridLayoutManager(context, 2, GridLayoutManager.HORIZONTAL, false)
            recyclerView.adapter?.apply {
                notifyDataSetChanged()
            }
            // Show view all button
            buttonClose.visibility = View.INVISIBLE
            buttonViewAll.visibility = View.VISIBLE
        }
    }
}