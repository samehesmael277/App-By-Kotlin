package com.sameh.testerappkotlin.ui.adapter

import com.sameh.testerappkotlin.model.entity.User

interface OnItemClick {
    fun onClick(user : User)
}