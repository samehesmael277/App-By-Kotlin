package com.sameh.testerappkotlin.model.remote

import com.sameh.testerappkotlin.model.entity.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ServiceAPI {

    @GET("samehesmael277/demo/users")
    suspend fun getAPIUsers(): Response<List<User>>

    @GET("samehesmael277/demo/users/{userId}")
    suspend fun getAPIUserFromId(@Path("userId") id: Int): Response<User>

    // ?userId =
    @GET("samehesmael277/demo/users/")
    suspend fun getAPIUserFromIdQuery(@Query("userId") id: Int): Response<User>

    @POST("samehesmael277/demo/users")
    suspend fun addAPIUser(@Body user: User): Response<User>

    @PUT("samehesmael277/demo/users/")
    suspend fun updateAPIUser(@Body user: User, @Query("userId") id: Int): Response<User>

    @DELETE("samehesmael277/demo/users/{userId}")
    suspend fun deleteAPIUser(@Path("userId") id: Int)

}