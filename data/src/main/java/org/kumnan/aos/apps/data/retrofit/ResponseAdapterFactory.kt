package org.kumnan.aos.apps.data.retrofit

import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import org.kumnan.aos.apps.domain.entity.status.Result
import java.lang.reflect.Type

internal class ResponseAdapterFactory : CallAdapter.Factory() {
    override fun get(returnType: Type, annotations: Array<out Annotation>, retrofit: Retrofit): CallAdapter<*, *>? {

        if (Call::class.java != getRawType(returnType)) return null
        check(returnType is ParameterizedType)

        val responseType = getParameterUpperBound(0, returnType)
        if (getRawType(responseType) != Result::class.java) return null
        check(responseType is ParameterizedType)


        val successType = getParameterUpperBound(0, responseType)

        return ResponseAdapter<Any>(successType)
    }
}