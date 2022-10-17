package com.gmail.zajcevserg.fakerapi.presentation.fragment.menu.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gmail.zajcevserg.domain.uimodels.FakerUiModel
import com.gmail.zajcevserg.fakerapi.R
import com.gmail.zajcevserg.fakerapi.databinding.ItemMenuBinding
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso


class CategoryRvAdapter(
    private val picasso: Picasso = Picasso.get()
) : ListAdapter<FakerUiModel, RecyclerView.ViewHolder>(MenuItemDiff) {

    companion object MenuItemDiff : DiffUtil.ItemCallback<FakerUiModel>() {

        override fun areContentsTheSame(
            oldItem: FakerUiModel,
            newItem: FakerUiModel
        ): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(
            oldItem: FakerUiModel,
            newItem: FakerUiModel
        ): Boolean {
            return oldItem == newItem
        }
    }

    inner class MenuItemViewHolder(
        private val binding: ItemMenuBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindView(position: Int) {
            binding.textViewName.text = currentList[position].title
            binding.textViewDescription.text = currentList[position].description
            val imageUrl = currentList[position].url
            picasso
                .load(imageUrl)
                .into(binding.imageViewPizza, object : Callback {
                    override fun onSuccess() {}
                    override fun onError(e: Exception?) {
                        binding.imageViewPizza.setImageResource(R.drawable.ic_baseline_cloud_off_24)
                    }
                })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int
    ): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemMenuBinding =
            ItemMenuBinding.inflate(inflater, parent, false)
        return MenuItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MenuItemViewHolder) {
            holder.bindView(position)
        }
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

}
