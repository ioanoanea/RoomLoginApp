package com.example.roomloginapp.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.roomloginapp.R

class CurrenciesRecyclerViewAdapter(private val items: Array<Item>) :
    RecyclerView.Adapter<CurrenciesRecyclerViewAdapter.ViewHolder>() {

    /**
     * Item class for this adapter
     */
    data class Item(
        val title: String,
        val type: String,
        val info: String,
        val image: String,
        val decremental: Boolean = false
    )

    /**
     * View Holder class for this adapter
     */
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTexView: TextView = itemView.findViewById(R.id.titleTextView)
        val typeTextView: TextView = itemView.findViewById(R.id.typeTextView)
        val infoTextView: TextView = itemView.findViewById(R.id.infoTextView)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.currency_card_layout, parent, false)

        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.titleTexView.text = items[position].title
        holder.typeTextView.text = items[position].type

        // set into text
        if (items[position].decremental) {
            holder.infoTextView.setTextColor(holder.itemView.context.resources.getColor(R.color.red))
            holder.infoTextView.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_down, 0)
            holder.infoTextView.text = "-${items[position].info}"
        } else {
            holder.infoTextView.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_up, 0)
            holder.infoTextView.text = "+${items[position].info}"
            holder.infoTextView.setTextColor(holder.itemView.context.resources.getColor(R.color.green))
        }

        Glide
            .with(holder.itemView.context)
            .load(items[position].image)
            .centerCrop()
            .placeholder(R.drawable.ic_diamond)
            .into(holder.imageView)
    }

    override fun getItemCount(): Int = items.size
}