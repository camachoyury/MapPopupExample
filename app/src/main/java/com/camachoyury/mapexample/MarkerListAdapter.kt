package com.camachoyury.mapexample

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.item.view.*

class MarkerListAdapter(val items: List<String>, val context: Context) : RecyclerView.Adapter<MarkerListAdapter.MarkerViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarkerViewHolder {

        return MarkerViewHolder(LayoutInflater.from(context).inflate(R.layout.item, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }


    override fun onBindViewHolder(holder: MarkerViewHolder, position: Int) {
        holder?.textViewInfoWindowItem?.text = items[position]
    }


    class MarkerViewHolder(@NonNull itemView: View): RecyclerView.ViewHolder(itemView) {
            val imageViewInfoWindowItem = itemView.imageViewInfoWindowItem
            val textViewInfoWindowItem = itemView.textViewInfoWindowItem



    }
}
