package com.example.android.marsphotos

import BaseTest
import com.example.android.marsphotos.network.MarsApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import junit.framework.Assert.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MarsApiServiceTest: BaseTest() {

    private lateinit var service: MarsApiService

    @Before
    fun setup(){
        val url = mockWebServer.url("/") //가로챌 url
        service = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(MoshiConverterFactory.create(
                Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()
            ))
            .build()
            .create(MarsApiService::class.java)
    }

    @Test
    fun api_service(){
        enqueue("mars_photos.json")
        runBlocking {
        //코루틴 실행./
            val apiResponse = service.getPhotos()
            assertNotNull(apiResponse)
            assertTrue("the list was empty", apiResponse.isNotEmpty())
            assertEquals("the IDs did not match", "424905", apiResponse[0].id)
        }
    }

}