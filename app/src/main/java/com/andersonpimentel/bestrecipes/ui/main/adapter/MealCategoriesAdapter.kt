package com.andersonpimentel.bestrecipes.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andersonpimentel.bestrecipes.app.load
import com.andersonpimentel.bestrecipes.databinding.ItemCategoriesBinding
import com.andersonpimentel.bestrecipes.ui.recipe.viewmodel.Category

class MealCategoriesAdapter(
    val onItemClick: ((item: Category, position: Int) -> Unit)? = null
) :
    RecyclerView.Adapter<MealCategoriesAdapter.MealCategoriesAdapterViewHolder>() {

    private var listMealCategories = arrayListOf<Category>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MealCategoriesAdapterViewHolder {
        return MealCategoriesAdapterViewHolder(
            ItemCategoriesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: MealCategoriesAdapterViewHolder,
        position: Int
    ) {
        holder.bind(listMealCategories[position])
    }

    override fun getItemCount(): Int {
        return listMealCategories.size
    }

    fun updateList(list: ArrayList<Category>) {
        listMealCategories = list
        notifyDataSetChanged()
    }

    inner class MealCategoriesAdapterViewHolder(
        private val binding: ItemCategoriesBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Category) {
            binding.tvTitleCategory.text = item.title
            binding.ivMealCategory.load(item.thumbUrl)
            binding.cardCategories.setOnClickListener {
                onItemClick?.invoke(item, adapterPosition)
            }
        }
    }
}
