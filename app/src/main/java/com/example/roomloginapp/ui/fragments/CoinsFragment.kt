package com.example.roomloginapp.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomloginapp.R
import com.example.roomloginapp.ui.adapters.CurrenciesListAdapter
import com.example.roomloginapp.viewmodel.CoinViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragrmet_coins.*

@AndroidEntryPoint
class CoinsFragment : Fragment() {

    private val viewModel: CoinViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragrmet_coins, container, false)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set recyclerView items
        val items = mutableListOf<CurrenciesListAdapter.Item>()

        viewModel.coins().observe(viewLifecycleOwner) { coins ->

            for (coin in coins) {
                items.add(CurrenciesListAdapter.Item(coin))
            }

            recyclerView.adapter?.apply {
                notifyDataSetChanged()
            }
        }

        // Set recyclerView
        val customAdapter = CurrenciesListAdapter(onClick = {
            Toast.makeText(context, "click", Toast.LENGTH_SHORT).show()
        })
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.apply {
            layoutManager = layoutManager
            adapter = customAdapter
        }

        customAdapter.submitList(items)
    }
}