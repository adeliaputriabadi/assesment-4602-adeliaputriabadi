package org.d3if3111.assesmentmobpro.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/adeliaputriabadi/assesment-4602-adeliaputriabadi/static-api/"
private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()
interface HandphoneApiService {
    @GET("handphone.json")
    suspend fun getHandphone(): String
}

object HandphoneApi {
    val service: HandphoneApiService by lazy {
        retrofit.create(HandphoneApiService::class.java)
    }
}