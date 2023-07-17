package com.example.myapplicationdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class MyAdapter(private val dataList: List<Pair<String, String>>)
  : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
  class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val titleTextView: TextView = itemView.findViewById((R.id.titleTextView))
    val contentTextView: TextView = itemView.findViewById(R.id.contentTextView)
  }

  interface OnItemClickListener {
    fun onItemClick(data: Pair<String, String>)
  }

  private var itemClickListener: OnItemClickListener? = null

  fun setOnItemClickListener(listener: OnItemClickListener) {
    itemClickListener = listener
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_content, parent, false)
    return MyViewHolder(view)
  }

  override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
    val (title, content) = dataList[position]
    holder.titleTextView.text = title
    holder.contentTextView.text = content

    holder.itemView.setOnClickListener {
      itemClickListener?.onItemClick(dataList[position])
    }
  }

  override fun getItemCount(): Int {
    return dataList.size
  }
}