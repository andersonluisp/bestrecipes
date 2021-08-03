package com.andersonpimentel.bestrecipes.ui.recipes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.andersonpimentel.bestrecipes.data.model.Meal
import com.andersonpimentel.bestrecipes.databinding.ItemRecipesBinding
import com.andersonpimentel.bestrecipes.ui.main.fragment.MainFragmentDirections
import com.andersonpimentel.bestrecipes.ui.recipes.fragment.RecipesFragmentDirections
import com.bumptech.glide.Glide

class RecipesFragmentAdapter: RecyclerView.Adapter<RecipesFragmentAdapter.RecipesFragmentAdapterViewHolder>() {

    private var mealsList = arrayListOf<Meal>()

    class RecipesFragmentAdapterViewHolder(
        private val binding: ItemRecipesBinding
    ): RecyclerView.ViewHolder(binding.root){

        fun bind(item: Meal){
            binding.tvRecipeTitle.text = item.strMeal

            Glide.with(binding.root)
                .load(item.strMealThumb)
                .into(binding.ivRecipe)

            binding.recipeCard.setOnClickListener {
                val direction = RecipesFragmentDirections.actionNavRecipeFragmentToNavRecipeDetailsFragment(item.idMeal, item.strMeal)
                binding.root.findNavController().navigate(direction)
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecipesFragmentAdapterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRecipesBinding.inflate(inflater, parent, false)
        return RecipesFragmentAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipesFragmentAdapterViewHolder, position: Int) {
        holder.bind(mealsList[position])
    }

    override fun getItemCount(): Int {
        return mealsList.size
    }

    fun setupRecyclerView(list: ArrayList<Meal>){
        mealsList = list
        notifyDataSetChanged()
    }


}
