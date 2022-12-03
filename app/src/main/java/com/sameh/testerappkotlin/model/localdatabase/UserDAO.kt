package com.sameh.testerappkotlin.model.localdatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sameh.testerappkotlin.model.entity.User

@Dao
interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("select * from user_table")
    suspend fun getUsers(): List<User>

}