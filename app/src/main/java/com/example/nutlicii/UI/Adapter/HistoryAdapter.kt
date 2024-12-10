package com.example.nutlicii.UI.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nutlicii.R
import com.example.nutlicii.data.model.HistoryItem

class HistoryAdapter(
    private val items: List<HistoryItem>,
    private val onItemClick: (HistoryItem) -> Unit // Listener untuk klik item
) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_history, parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val item = items[position]
        holder.date.text = item.date
        holder.title.text = item.title
        holder.type.text = item.type
        holder.description.text = item.description
        holder.icon.text = item.icon

        // Listener klik pada setiap item
        holder.itemView.setOnClickListener {
            onItemClick(item)
        }
    }

    override fun getItemCount(): Int = items.size

    class HistoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val date: TextView = view.findViewById(R.id.tv_date)
        val title: TextView = view.findViewById(R.id.tv_title)
        val type: TextView = view.findViewById(R.id.tv_type)
        val description: TextView = view.findViewById(R.id.tv_description)
        val icon: TextView = view.findViewById(R.id.tv_icon)
    }
}
