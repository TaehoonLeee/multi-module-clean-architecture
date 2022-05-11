package com.example.data.network.retrofit

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {

	var token: String? = null
		private set

	@EntryPoint
	@InstallIn(SingletonComponent::class)
	interface AuthInterceptorInterface {
		fun getAuthInterceptor(): AuthInterceptor
	}

	fun setToken(token: String) {
		this.token = token
	}

	override fun intercept(chain: Interceptor.Chain): Response {
		return token?.let {
			chain.proceed(
				chain.request()
					.newBuilder()
					.addHeader("AccessToken", it)
					.build()
			)
		}?: chain.proceed(chain.request())
	}
}