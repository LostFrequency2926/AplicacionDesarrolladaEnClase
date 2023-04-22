package com.danielfmunoz.myfirstform.ui.data

sealed class ResourceRemote<T>(

    var data: T? = null,
    var message: String? = null,
    var status: Status? = null,
    var errorCode: Int? = null
) {
    class Success<T>(data: T) : ResourceRemote<T>(data = data, status = Status.Success)
    class Error<T>(message: String?) : ResourceRemote<T>(message = message, status = Status.Loading)
    class Loading<T>(errorCode: Int? = null, message: String? = null) :
        ResourceRemote<T>(errorCode = errorCode, message = message, status = Status.Error)
}

enum class Status {
    Success {
    },
    Error {
    },
    Loading {
    }
}