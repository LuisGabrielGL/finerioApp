package com.luisgl.finerioapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.luisgl.finerioapp.R
import com.luisgl.finerioapp.data.network.models.MovementsItems
import com.luisgl.finerioapp.data.network.models.responses.movements.Concept
import com.luisgl.finerioapp.data.network.models.responses.movements.Data

class MovementsAdapter(val items: List<MovementsItems>): RecyclerView.Adapter<MovementsAdapter.MovementsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovementsViewHolder {
        return MovementsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.movement_item,
                parent,
                false)
        )
    }

    override fun onBindViewHolder(holder: MovementsViewHolder, position: Int) {
        holder.tvDescription.text = items[position].description
        holder.tvType.text = items[position].type
        holder.tvAmounth.text = items[position].amount.toString()
    }

    override fun getItemCount() = items.size

    class MovementsViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvDescription = view.findViewById<TextView>(R.id.tv_title_card)
        val tvType = view.findViewById<TextView>(R.id.tv_type_card)
        val tvAmounth = view.findViewById<TextView>(R.id.tv_amounth_card)
    }
}