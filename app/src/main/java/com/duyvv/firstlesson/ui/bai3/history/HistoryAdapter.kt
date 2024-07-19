package com.duyvv.firstlesson.ui.bai3.history

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.duyvv.firstlesson.databinding.ItemHistoryBinding
import com.duyvv.firstlesson.domain.History

class HistoryAdapter : Adapter<HistoryAdapter.HistoryViewHolder>() {
    inner class HistoryViewHolder(
        val binding: ItemHistoryBinding,
    ) : ViewHolder(binding.root)

    private val items = mutableListOf<History>()

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: List<History>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): HistoryViewHolder =
        HistoryViewHolder(
            ItemHistoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            ),
        )

    override fun onBindViewHolder(
        holder: HistoryViewHolder,
        position: Int,
    ) {
        val item = items[position]
        holder.binding.tvUp.text = if (item.isUp == true) "UP!" else "DOWN!"
        holder.binding.tvTitle.text = item.title ?: ""
    }

    override fun getItemCount() = items.size
}
