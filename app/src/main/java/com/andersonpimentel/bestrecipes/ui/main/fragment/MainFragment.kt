package com.andersonpimentel.bestrecipes.ui.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.andersonpimentel.bestrecipes.app.gone
import com.andersonpimentel.bestrecipes.app.visible
import com.andersonpimentel.bestrecipes.databinding.MainFragmentBinding
import com.andersonpimentel.bestrecipes.ui.main.adapter.MealCategoriesAdapter
import com.andersonpimentel.bestrecipes.ui.main.viewmodel.MainViewModel

class MainFragment : Fragment() {
    private val vm by viewModels<MainViewModel>()
    private lateinit var b: MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        b = MainFragmentBinding.inflate(inflater)
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        b.rvMealsCategories.apply {
            layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
            adapter = MealCategoriesAdapter(
                onItemClick = { item, _ ->
                    navToRecipeFragment(item.title)
                }
            )
        }
    }

    private fun navToRecipeFragment(recipeTitle: String) {
        findNavController().navigate(
            MainFragmentDirections.actionMainFragmentToRecipeFragment(recipeTitle)
        )
    }

    private fun setupObservers() {
        vm.mealsCategoriesLiveData.observe(
            viewLifecycleOwner,
            {
                (b.rvMealsCategories.adapter as MealCategoriesAdapter).updateList(it)
                setListViewState()
            }
        )

        vm.exception.observe(
            viewLifecycleOwner,
            {
                setErrorState(it.message)
            }
        )
    }

    private fun setErrorState(errorMessage: String?) {
        b.apply {
            pbProgressCircular.gone()
            rvMealsCategories.gone()
            tvErrorApi.apply {
                text = errorMessage
                visible()
            }
        }
    }

    private fun setListViewState() {
        b.pbProgressCircular.gone()
        b.rvMealsCategories.visible()
    }
}
