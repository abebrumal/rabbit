package com.example.whiterabbit.data.network

import com.example.whiterabbit.data.responses.EmployeeResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit


private const val BASE_URL_DEV = "http://www.mocky.io/v2/"

interface ApiInterface {

    companion object{
        operator fun invoke(
            networkConnectionInterceptor : NetworkConnectionInterceptor
        ): ApiInterface {

            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(3, TimeUnit.MINUTES)
                .readTimeout(3, TimeUnit.MINUTES)
                .writeTimeout(3, TimeUnit.MINUTES)
                .addInterceptor(networkConnectionInterceptor)
                .build()



            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL_DEV)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiInterface::class.java)
        }


    }


    @GET("5d565297300000680030a986")
    suspend fun getEmployees() : Response<EmployeeResponse>
}