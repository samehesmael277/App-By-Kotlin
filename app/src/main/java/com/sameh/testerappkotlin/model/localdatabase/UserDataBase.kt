package com.sameh.testerappkotlin.model.localdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sameh.testerappkotlin.model.entity.User

private const val DATABASE_NAME = "user_database"

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDataBase : RoomDatabase() {

    abstract fun userDAO(): UserDAO

    companion object {

        // singleton pattern
        // make it visible for any thread
        @Volatile
        private var INSTANCE: UserDataBase? = null

        fun getInstance(context: Context): UserDataBase {
            return INSTANCE ?: synchronized(Any()) {
                INSTANCE ?: buildDataBase(context).also{ INSTANCE =it }
            }
        }

        // for build local data base
        private fun buildDataBase(context: Context): UserDataBase {
            return Room.databaseBuilder(
                context.applicationContext, UserDataBase::class.java, DATABASE_NAME
            )
                .build()
        }

    }

}