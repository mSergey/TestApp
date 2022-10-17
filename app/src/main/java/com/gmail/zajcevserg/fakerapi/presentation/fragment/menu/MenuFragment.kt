package com.gmail.zajcevserg.fakerapi.presentation.fragment.menu


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.gmail.zajcevserg.fakerapi.R
import com.gmail.zajcevserg.fakerapi.databinding.FragmentMenuBinding
import com.gmail.zajcevserg.fakerapi.presentation.fragment.menu.categories.CategoryMenuFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.core.component.KoinComponent

class MenuFragment : Fragment(), KoinComponent {
    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!
    private val menuViewModel by sharedViewModel<MenuViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        menuViewModel.liveModels.observe(viewLifecycleOwner) {

            binding.viewPager.adapter = CategoryPageAdapter(
                listOf("Pizza", "Cake", "Coffee", "Cheese", "Lollipop")
            )
            binding.viewPager.isUserInputEnabled = true

            TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
                tab.setCustomView(R.layout.tab_item_layout)
                val categoryTextView =
                    tab.customView?.findViewById<TextView>(R.id.category_text)
                categoryTextView?.text = listOf("Pizza", "Cake", "Coffee", "Cheese", "Lollipop")[position]
            }.attach()

        }

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private inner class CategoryPageAdapter(
        private val categoryArgs: List<String>
    ) : FragmentStateAdapter(childFragmentManager, lifecycle) {
        override fun getItemCount(): Int {
            return categoryArgs.size
        }

        override fun createFragment(position: Int): Fragment {
            val fragment = CategoryMenuFragment()
            fragment.arguments = Bundle().apply {
                putString("CATEGORY_ARG", categoryArgs[position])
            }
            return fragment
        }
    }

}