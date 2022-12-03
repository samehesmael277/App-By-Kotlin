package com.sameh.testerappkotlin.model.localdatabase

import com.sameh.testerappkotlin.model.entity.User

interface LocalRepository {

    suspend fun getUsers(): List<User>

    suspend fun deleteUser(user: User)

    suspend fun insertOrUpdateUser(user: User)

}