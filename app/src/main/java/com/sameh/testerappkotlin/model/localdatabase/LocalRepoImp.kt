package com.sameh.testerappkotlin.model.localdatabase

import com.sameh.testerappkotlin.model.entity.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalRepoImp(private val db: UserDataBase) : LocalRepository {

    override suspend fun getUsers() =
        withContext(Dispatchers.IO) {
            db.userDAO().getUsers()
        }

    override suspend fun deleteUser(user: User) {
        withContext(Dispatchers.IO) {
            db.userDAO().deleteUser(user)
        }
    }

    override suspend fun insertOrUpdateUser(user: User) {
        withContext(Dispatchers.IO) {
            db.userDAO().insertOrUpdateUser(user)
        }
    }

}