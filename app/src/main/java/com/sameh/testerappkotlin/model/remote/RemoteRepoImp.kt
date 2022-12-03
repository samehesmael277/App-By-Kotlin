package com.sameh.testerappkotlin.model.remote

import android.util.Log
import com.sameh.testerappkotlin.model.entity.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteRepoImp(private val api: ServiceAPI) : RemoteRepo {

    override suspend fun getAPIUsers() =
        withContext(Dispatchers.IO) {
            api.getAPIUsers()

        }

    override suspend fun getAPIUserFromId(id: Int) =
        withContext(Dispatchers.IO) {
            api.getAPIUserFromId(id)

        }

    override suspend fun getAPIUserFromIdQuery(id: Int) =
        withContext(Dispatchers.IO) {
            api.getAPIUserFromIdQuery(id)

        }

    override suspend fun addAPIUser(user: User) =
        withContext(Dispatchers.IO) {
            api.addAPIUser(user)
        }

    override suspend fun updateAPIUser(user: User, id: Int) =
        withContext(Dispatchers.IO) {
            api.updateAPIUser(user, id)
        }

    override suspend fun deleteAPIUser(id: Int) {
        withContext(Dispatchers.IO) {
            api.deleteAPIUser(id)
        }
    }

}