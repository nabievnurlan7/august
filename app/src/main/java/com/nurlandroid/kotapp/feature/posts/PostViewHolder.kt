package com.nurlandroid.kotapp.feature.posts

import android.view.ViewGroup
import com.nurlandroid.kotapp.R
import com.nurlandroid.kotapp.common.base.BaseViewHolder
import com.nurlandroid.kotapp.databinding.ItemPostBinding

class PostViewHolder(
        parent: ViewGroup,
        listener: (Int, Int, Post) -> Unit
) : BaseViewHolder<Post>(R.layout.item_post, parent, listener) {

    private val binding: ItemPostBinding = ItemPostBinding.bind(itemView)

    override fun onBind(item: Post, selected: Boolean) {
        super.onBind(item, selected)

        with(binding) {
            titleTextView.text = item.title
        }
    }
}