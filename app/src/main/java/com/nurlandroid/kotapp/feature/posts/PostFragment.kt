package com.nurlandroid.kotapp.feature.posts

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.nurlandroid.kotapp.R
import com.nurlandroid.kotapp.common.ui.UiState
import com.nurlandroid.kotapp.common.base.BaseFragment
import com.nurlandroid.kotapp.databinding.FragmentPostsBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class PostFragment : BaseFragment(R.layout.fragment_posts) {

    private val postViewModel: PostViewModel by viewModel()
    private val viewBinding: FragmentPostsBinding by viewBinding()
    private lateinit var postAdapter: PostAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            postViewModel.uiState.collect {
                when (it) {
                    is UiState.Loading -> showProgress()
                    is UiState.Data -> {
                        closeProgress()
                        postAdapter.setItems(it.data)
                    }
                    is UiState.Error -> showError(it.errorType)
                }
            }
        }
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