package org.d3if3111.assesmentmobpro.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.d3if3111.assesmentmobpro.model.Handphone
import org.d3if3111.assesmentmobpro.model.OpStatus
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.DELETE

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

private const val BASE_URL = "https://unspoken.my.id/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()
interface HandphoneApiService {
    @GET("api_adel.php")
    suspend fun getHandphone(
        @Header("Authorization") userId: String
    ): List<Handphone>

    @Multipart
    @POST("api_adel.php")
    suspend fun postHandphone(
        @Header("Authorization") userId: String,
        @Part("name") name: RequestBody,
        @Part("type") type: RequestBody,
        @Part image: MultipartBody.Part
    ): OpStatus

    @DELETE("api_adel.php")
    suspend fun deleteHandphone(
        @Header("Authorization") userId: String,
        @Query("id") id: String
    ): OpStatus

}

object HandphoneApi {
    val service: HandphoneApiService by lazy {
        retrofit.create(HandphoneApiService::class.java)
    }

    fun getHandphoneUrl(imageId: String): String {
        return "${BASE_URL}image.php?id=$imageId"
    }


}
enum class ApiStatus {LOADING, SUCCESS, FAILED}