package com.nurlandroid.kotapp.common

import retrofit2.Response

object ResponseHandler {
    fun <T> handleResponse(response: Response<T>, nextPage: Int? = null, pageSize: Int = 0): RepositoryResult<T> {
        require(response.isSuccessful) {
            val errorCode = response.code()
            return RepositoryResult.error(errorMessage = "$NETWORK_ERROR = $errorCode ")
        }

        val body = response.body()

        return if (body != null) {
            val pageStatus = nextPage?.let { setPageStatus((body as List<*>).size, it, pageSize) }
            RepositoryResult.success(fetchedData = body, pageStatus = pageStatus)
        } else {
            RepositoryResult.error(errorMessage = NO_DATA)
        }
    }

    private fun setPageStatus(listSize: Int, loadedPage: Int, pageSize: Int): PageLoadStatus =
            PageLoadStatus(isOnLoading = false, isOnLast = listSize < pageSize, page = loadedPage)
}