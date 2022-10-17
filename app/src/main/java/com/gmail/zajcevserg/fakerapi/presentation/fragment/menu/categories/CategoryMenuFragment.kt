package com.gmail.zajcevserg.fakerapi.presentation.fragment.menu.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gmail.zajcevserg.domain.result.Result
import com.gmail.zajcevserg.domain.uimodels.FakerUiModel
import com.gmail.zajcevserg.fakerapi.databinding.FragmentMenuCategoryBinding
import com.gmail.zajcevserg.fakerapi.presentation.fragment.menu.MenuViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class CategoryMenuFragment : Fragment() {

    private var _binding: FragmentMenuCategoryBinding? = null
    private val binding get() = _binding!!
    private val menuViewModel by sharedViewModel<MenuViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMenuCategoryBinding.inflate(inflater, container, false)
        val categoryRvAdapter = CategoryRvAdapter()
        with(binding.menuRv) {
            adapter = categoryRvAdapter
            layoutManager = LinearLayoutManager(context).apply {
                orientation = RecyclerView.VERTICAL
            }
        }
        menuViewModel.liveModels.observe(viewLifecycleOwner) {
            when (it) {
                is Result.Success<*> -> {
                    categoryRvAdapter.submitList(it.successBody as List<FakerUiModel>)
                }
                is Result.Error -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }
            }
        }

        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}