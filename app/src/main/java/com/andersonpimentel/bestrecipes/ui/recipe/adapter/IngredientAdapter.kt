package com.andersonpimentel.bestrecipes.ui.recipe.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andersonpimentel.bestrecipes.data.model.Ingredient
import com.andersonpimentel.bestrecipes.databinding.IngredientsRecipeBinding

class IngredientAdapter : RecyclerView.Adapter<IngredientAdapter.IngredientAdapterViewHolder>() {

    private var ingredientsList = arrayListOf<Ingredient>()

    class IngredientAdapterViewHolder(
        private val binding: IngredientsRecipeBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Ingredient) {
            binding.cbIngredient.text = "${item.measure} ${item.name}"
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): IngredientAdapterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = IngredientsRecipeBinding.inflate(inflater, parent, false)
        return IngredientAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IngredientAdapterViewHolder, position: Int) {
        holder.bind(ingredientsList[position])
    }

    override fun getItemCount(): Int {
        return ingredientsList.size
    }

    fun setupRecyclerView(list: ArrayList<Ingredient>) {
        ingredientsList = list
        notifyDataSetChanged()
    }
}