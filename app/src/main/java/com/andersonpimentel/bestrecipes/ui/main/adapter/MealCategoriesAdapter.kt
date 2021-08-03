package com.andersonpimentel.bestrecipes.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.andersonpimentel.bestrecipes.data.model.Category
import com.andersonpimentel.bestrecipes.databinding.ItemCategoriesBinding
import com.andersonpimentel.bestrecipes.ui.main.fragment.MainFragmentDirections
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
                binding.cardCategories.setOnClickListener {
                    val direction = MainFragmentDirections.actionMainFragmentToRecipeFragment(item.strCategory)
                    binding.root.findNavController().navigate(direction)
                }
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

