package com.sameh.testerappkotlin.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(

    @PrimaryKey(autoGenerate = true)
    var userId: Int,

    var userName: String,

    var userMessage: String,

    var idUserImg: Int

)