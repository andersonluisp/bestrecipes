package com.andersonpimentel.bestrecipes.ui.recipes.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import com.andersonpimentel.bestrecipes.databinding.RecipesFragmentBinding
import com.andersonpimentel.bestrecipes.ui.recipes.adapter.RecipesFragmentAdapter
import com.andersonpimentel.bestrecipes.ui.recipes.viewmodel.RecipesViewModel

class RecipesFragment : Fragment() {

    private val args: RecipesFragmentArgs by navArgs()
    private lateinit var b: RecipesFragmentBinding
    private val vm by viewModels<RecipesViewModel>()
    private val adapter by lazy { RecipesFragmentAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        b = RecipesFragmentBinding.inflate(layoutInflater)
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        setupRecyclerView()

        vm.getRecipesByCategory(args.categoryRecipes)
    }

    private fun setupRecyclerView() {
        val decoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        b.rvRecipes.addItemDecoration(decoration)
        b.rvRecipes.adapter = adapter
    }

    private fun setupObservers() {
        vm.recipes.observe(
            viewLifecycleOwner,
            {
                it.meals?.let { items ->
                    adapter.setupRecyclerView(items)
                }
            }
        )
    }
}
