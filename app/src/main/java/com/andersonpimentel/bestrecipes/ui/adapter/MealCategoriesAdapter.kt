package com.andersonpimentel.bestrecipes.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.andersonpimentel.bestrecipes.data.model.Category
import com.andersonpimentel.bestrecipes.databinding.ItemCategoriesBinding
import com.andersonpimentel.bestrecipes.databinding.MainActivityBinding
import com.bumptech.glide.Glide

class MealCategoriesAdapter:RecyclerView.Adapter<MealCategoriesAdapter.MealCategoriesAdapterViewHolder>() {

    private var listMealCategories = arrayListOf<Category>()

    class MealCategoriesAdapterViewHolder(
        private val binding: ItemCategoriesBinding
        ): RecyclerView.ViewHolder(binding.root){

            fun bind (item: Category){
                binding.tvTitleCategory.text = item.strCategory
                Glide.with(binding.root)
                    .load(item.strCategoryThumb)
                    .into(binding.ivMealCategory)
            }

        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MealCategoriesAdapter.MealCategoriesAdapterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCategoriesBinding.inflate(inflater, parent, false)
        return MealCategoriesAdapter.MealCategoriesAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MealCategoriesAdapter.MealCategoriesAdapterViewHolder, position: Int) {
        holder.bind(listMealCategories[position])
    }

    override fun getItemCount(): Int {
        return listMealCategories.size
    }

    fun setupRecyclerView(list: ArrayList<Category>){
        listMealCategories = list
        notifyDataSetChanged()
    }
}

