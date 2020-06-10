package com.example.whiterabbit.data.network

import android.content.Context
import android.net.ConnectivityManager
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionInterceptor(
    private val context: Context
) : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        try {
            if (!isNetworkAvailable())
                throw NoInternetException("Make sure you've an active internet connection")

            return chain.proceed(chain.request())
        } catch (e: Exception) {
            throw CustomApiException("Oops! Something went wrong. Please try again later.")

        }
    }

    private fun isNetworkAvailable() : Boolean{
        val connectivityManager =
            context.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        connectivityManager.activeNetworkInfo.also {
            return it != null && it.isConnected
        }
    }
}