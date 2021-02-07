package com.nurlandroid.kotapp.feature.posts

import com.nurlandroid.kotapp.NetworkApi
import com.nurlandroid.kotapp.common.network.NetworkResult
import com.nurlandroid.kotapp.common.network.ResponseHandler

class PostRepository(private val service: NetworkApi) {

    suspend fun loadData(): NetworkResult<List<Post>> {
        val response = service.getPosts()
        return ResponseHandler.handleResponse(response)
    }
}