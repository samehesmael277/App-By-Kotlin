package com.sameh.testerappkotlin.ui.fragment.userlist

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sameh.testerappkotlin.model.entity.User
import com.sameh.testerappkotlin.model.localdatabase.LocalRepoImp
import com.sameh.testerappkotlin.model.localdatabase.UserDataBase
import com.sameh.testerappkotlin.model.remote.RemoteRepoImp
import com.sameh.testerappkotlin.model.remote.RetrofitBuilder
import com.sameh.testerappkotlin.model.remote.ServiceAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    // for local
    private var _userMutableLiveData = MutableLiveData<List<User>>()
    val userLiveData: LiveData<List<User>> get() = _userMutableLiveData

    // for remote
    // for get all users
    private var _userAPIMutableLiveData = MutableLiveData<List<User>>()
    val userApiLiveData: LiveData<List<User>> get() = _userAPIMutableLiveData

    // for add user
    private var _addUserAPIMutableLiveData = MutableLiveData<User>()
    val addUserAPILiveData: LiveData<User> get() = _addUserAPIMutableLiveData

    private val localRepoImp: LocalRepoImp
    private val remoteRepoImp: RemoteRepoImp

    init {
        // local
        val db = UserDataBase.getInstance(application)
        localRepoImp = LocalRepoImp(db)

        // remote
        val serviceInstance = RetrofitBuilder.getRetroBuilder().create(ServiceAPI::class.java)
        remoteRepoImp = RemoteRepoImp(serviceInstance)
    }

    // local
    fun getAllUsers() = viewModelScope.launch {
        _userMutableLiveData.postValue(localRepoImp.getUsers())
    }

    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            localRepoImp.insertOrUpdateUser(user)
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            localRepoImp.deleteUser(user)
        }
    }

    // remote
    fun getUsersFromAPI() {
        viewModelScope.launch {
            try {
                val result = remoteRepoImp.getAPIUsers()

                if (result.isSuccessful) {
                    if (result.body() != null) {
                        _userAPIMutableLiveData.postValue(result.body())
                    }
                } else {
                    Log.i("errorMsg", result.message())
                }
            } catch (e: Exception) {
                Log.i("errorMsg", "${e.message}")
            }
        }
    }

    fun addUserAPI(user: User) {
        viewModelScope.launch {
            try {
                val result = remoteRepoImp.addAPIUser(user)

                if (result.isSuccessful) {
                    if (result.body() != null) {
                        _addUserAPIMutableLiveData.postValue(result.body())
                    } else {
                        Log.i("errorMsg", result.message())
                    }
                }
            } catch (e: Exception) {
                Log.i("errorMsg", e.message.toString())
            }
        }
    }

    fun deleteAPIUser(id: Int) {
        viewModelScope.launch {
            try {
                remoteRepoImp.deleteAPIUser(id)
            } catch (e: Exception) {
                Log.i("errorMsg", e.message.toString())
            }
        }
    }

}