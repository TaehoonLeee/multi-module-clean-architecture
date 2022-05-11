package com.example.data.network.retrofit.factory

import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type
import com.example.domain.model.status.Result

internal class ResponseAdapter<T>(
    private val successType : Type
) : CallAdapter<T, Call<Result<T>>> {
    override fun responseType(): Type = successType

    override fun adapt(call: Call<T>): Call<Result<T>> = ResponseCall(call)
}