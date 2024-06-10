package org.d3if3111.assesmentmobpro.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if3111.assesmentmobpro.model.Handphone
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/adeliaputriabadi/assesment-4602-adeliaputriabadi/static-api/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()
interface HandphoneApiService {
    @GET("handphone.json")
    suspend fun getHandphone(): List<Handphone>
}

object HandphoneApi {
    val service: HandphoneApiService by lazy {
        retrofit.create(HandphoneApiService::class.java)
    }
}