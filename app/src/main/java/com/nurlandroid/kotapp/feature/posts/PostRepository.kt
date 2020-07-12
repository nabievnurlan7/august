package com.nurlandroid.kotapp.feature.posts

import com.nurlandroid.kotapp.NetworkApi
import com.nurlandroid.kotapp.common.RepositoryResult
import com.nurlandroid.kotapp.common.ResponseHandler

class PostRepository(private val service: NetworkApi) {

    suspend fun loadData(): RepositoryResult<List<Post>> {
        val response = service.getPosts()
        return ResponseHandler.handleResponse(response)
    }
}