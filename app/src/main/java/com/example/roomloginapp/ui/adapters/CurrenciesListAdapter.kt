package com.example.roomloginapp.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.roomloginapp.R
import com.example.roomloginapp.data.model.Coin

class CurrenciesListAdapter(private val onClick: (Item) -> Unit) :
    ListAdapter<CurrenciesListAdapter.Item, CurrenciesListAdapter.ViewHolder>(CurrenciesDiffCallback) {

    /**
     * Item class for this adapter
     */
    data class Item(
        val id: String,
        val symbol: String,
        val name: String
    ) {
        constructor(coin: Coin) : this(coin.id, coin.symbol, coin.name)
    }

    /**
     * View Holder class for this adapter
     */
    class ViewHolder(itemView: View, val onClick: (Item) -> Unit) : RecyclerView.ViewHolder(itemView) {
        private val titleTexView: TextView = itemView.findViewById(R.id.titleTextView)
        private val idTextView: TextView = itemView.findViewById(R.id.idTextView)
        private val symbolTextView: TextView = itemView.findViewById(R.id.symbolTextView)
        private val imageView: ImageView = itemView.findViewById(R.id.imageView)
        private var currentItem: Item? = null

        init {
            itemView.setOnClickListener {
                currentItem?.let {
                    onClick(it)
                }
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind(item: Item) {
            titleTexView.text = item.name
            symbolTextView.text = item.symbol
            idTextView.text = item.id

            /*
            // set into text
            if (item.decremental) {
                infoTextView.setTextColor(itemView.context.resources.getColor(R.color.red))
                infoTextView.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_down, 0)
                infoTextView.text = "-${item.info}"
            } else {
                infoTextView.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_up, 0)
                infoTextView.text = "+${item.info}"
                infoTextView.setTextColor(itemView.context.resources.getColor(R.color.green))
            }
             */

            Glide
                .with(itemView.context)
                .load("")
                .centerCrop()
                .placeholder(R.drawable.ic_diamond)
                .into(imageView)

        }
    }

    /**
     * Diff Callback class for this adapter
     */
    object CurrenciesDiffCallback : DiffUtil.ItemCallback<Item>() {

        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }
    }

    // Creates and inflates view and return ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.coin_card_layout, parent, false)

        return ViewHolder(view, onClick)
    }

    // Gets current item and uses it to bind view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}