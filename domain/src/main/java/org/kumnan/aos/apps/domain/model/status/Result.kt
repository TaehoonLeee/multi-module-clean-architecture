package org.kumnan.aos.apps.domain.model.status

sealed class Result<T> {

    class Success<T>(val data: T, val code: Int) : Result<T>()

    class Loading<T> : Result<T>()

    class ApiError<T>(val message: String, val code: Int) : Result<T>()

    class NetworkError<T>(val throwable: Throwable) : Result<T>()

    class NullResult<T> : Result<T>()
}
