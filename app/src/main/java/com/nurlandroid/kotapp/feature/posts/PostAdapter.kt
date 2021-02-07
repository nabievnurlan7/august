package com.nurlandroid.kotapp.feature.posts

import android.view.ViewGroup
import com.nurlandroid.kotapp.common.base.BaseAdapter
import com.nurlandroid.kotapp.common.base.BaseViewHolder

class PostAdapter(
        private val listener: (Int, Int, Post) -> Unit
) : BaseAdapter<Post>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Post> =
            PostViewHolder(parent, listener)
}