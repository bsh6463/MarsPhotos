package com.example.android.marsphotos.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com"
private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
private val retrofit= Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface MarsApiService {
    @GET("photos")
    suspend fun getPhotos(): List<MarsPhoto>


}

//object로 선언하면 싱글톤 객체
//MarsApi는 싱글톤 객체임. 해당 객체 안에 retrofit객체가 있음.
//따라서 MarsApi.retrofirService를 호출하면 retrofit객체를 싱글톤 객체처럼 사용할 수 잇음.
object MarsApi{
    //lazy 지연초기화, 최초사용 시 초기화됨.
    val retrofitService: MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java)
    }
}