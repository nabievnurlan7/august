package com.nurlandroid.kotapp.feature.posts

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.nurlandroid.kotapp.R
import com.nurlandroid.kotapp.common.base.BaseFragment
import com.nurlandroid.kotapp.databinding.FragmentPostsBinding
import com.nurlandroid.kotapp.feature.posts.PostViewModel.UiState
import org.koin.androidx.viewmodel.ext.android.viewModel

class PostFragment : BaseFragment(R.layout.fragment_posts) {

    private val postViewModel: PostViewModel by viewModel()
    private val viewBinding: FragmentPostsBinding by viewBinding()
    private lateinit var postAdapter: PostAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postViewModel.posts.observe(viewLifecycleOwner, {
            it?.let {
                when (it) {
                    is UiState.Loading -> showProgress()
                    is UiState.Data -> {
                        closeProgress()
                        postAdapter.setItems(it.dataList)
                    }
                    is UiState.Error -> showError(it.errorType)
                }
            }
        })
    }

    override fun setUI() {
        super.setUI()

        postAdapter = PostAdapter { _, _, item ->
        }

        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        viewBinding.catalogRecyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = postAdapter
        }
    }
}