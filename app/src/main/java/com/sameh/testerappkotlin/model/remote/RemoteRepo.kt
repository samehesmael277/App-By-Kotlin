package com.sameh.testerappkotlin.model.remote

import com.sameh.testerappkotlin.model.entity.User
import retrofit2.Response
import retrofit2.http.*

interface RemoteRepo {

    suspend fun getAPIUsers(): Response<List<User>>

    suspend fun getAPIUserFromId(id: Int): Response<User>

    suspend fun getAPIUserFromIdQuery(id: Int): Response<User>

    suspend fun addAPIUser(user: User): Response<User>

    suspend fun updateAPIUser(user: User, id: Int): Response<User>

    suspend fun deleteAPIUser(id: Int)

}