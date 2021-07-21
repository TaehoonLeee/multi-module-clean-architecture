package org.kumnan.aos.apps.data.retrofit

import org.kumnan.aos.apps.domain.entity.status.Result
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

internal class ResponseAdapter<T>(
    private val successType : Type
) : CallAdapter<T, Call<Result<T>>> {
    override fun responseType(): Type = successType

    override fun adapt(call: Call<T>): Call<Result<T>> = ResponseCall(call)
}