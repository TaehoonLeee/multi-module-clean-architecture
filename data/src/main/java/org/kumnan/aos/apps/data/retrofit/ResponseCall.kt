package org.kumnan.aos.apps.data.retrofit

import okhttp3.Request
import okio.Timeout
import org.kumnan.aos.apps.domain.entity.status.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.UnsupportedOperationException

internal class ResponseCall<T> constructor(
    private val callDelegate: Call<T>
) : Call<Result<T>> {

    override fun enqueue(callback: Callback<Result<T>>) = callDelegate.enqueue(object : Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {
            response.body()?.let {
                when(response.code()) {
                    in 200..208 -> {
                        callback.onResponse(this@ResponseCall, Response.success(Result.Success(it, response.code())))
                    }
                    in 400..409 -> {
                        callback.onResponse(this@ResponseCall, Response.success(Result.ApiError(response.message(), response.code())))
                    }
                }
            }?: callback.onResponse(this@ResponseCall, Response.success(Result.NullResult()))
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            callback.onResponse(this@ResponseCall, Response.success(Result.NetworkError(t)))
            call.cancel()
        }
    })

    override fun clone(): Call<Result<T>> = ResponseCall(callDelegate.clone())

    override fun execute(): Response<Result<T>> = throw UnsupportedOperationException("ResponseCall does not support execute.")

    override fun isExecuted(): Boolean = callDelegate.isExecuted

    override fun cancel() = callDelegate.cancel()

    override fun isCanceled(): Boolean = callDelegate.isCanceled

    override fun request(): Request = callDelegate.request()

    override fun timeout(): Timeout = callDelegate.timeout()
}