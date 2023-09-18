package com.example.mswipetask

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CategoryAdapter(private var categoryList: List<Category>) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categoryList[position]
        holder.bind(category)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(category: Category) {
            val categoryNameTextView = itemView.findViewById<TextView>(R.id.categoryNameTextView)
            categoryNameTextView.text = category.name
             val expandCollapseImageView: ImageView = itemView.findViewById(R.id.categoryIconImageView)


            val itemRecyclerView = itemView.findViewById<RecyclerView>(R.id.itemRecyclerView)
            val itemAdapter = ItemAdapter(category.items)

            // Set the visibility of the itemRecyclerView based on isExpanded
            if (category.isExpanded) {
                expandCollapseImageView.setImageResource(R.drawable.baseline_expand_less_24) // Set your collapse icon

                itemRecyclerView.visibility = View.VISIBLE
            } else {
                expandCollapseImageView.setImageResource(R.drawable.baseline_expand_more_24) // Set your expand icon

                itemRecyclerView.visibility = View.GONE
            }

            // Toggle isExpanded when the category is clicked
            categoryNameTextView.setOnClickListener {
                category.isExpanded = !category.isExpanded
                notifyItemChanged(adapterPosition)
            }

            expandCollapseImageView.setOnClickListener {
                category.isExpanded = !category.isExpanded
                notifyItemChanged(adapterPosition)
            }

            itemRecyclerView.adapter = itemAdapter
            itemRecyclerView.layoutManager = LinearLayoutManager(itemView.context)
        }
    }

    fun updateData(newCategoryList: List<Category>) {
        categoryList = newCategoryList
        notifyDataSetChanged()
    }

    }
