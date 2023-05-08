package com.danielfmunoz.myfirstform.ui.bottomMenu.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danielfmunoz.myfirstform.ui.data.ResourceRemote
import com.danielfmunoz.myfirstform.ui.data.UserRepository
import com.danielfmunoz.myfirstform.ui.model.User
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.launch

class MyProfileViewModel : ViewModel() {

    private var userRepository = UserRepository()

    private val _errorMsg: MutableLiveData<String?> = MutableLiveData()
    val errorMsg: LiveData<String?> = _errorMsg

    private val _userLoaded: MutableLiveData<User?> = MutableLiveData()
    val userLoaded: LiveData<User?> = _userLoaded

    fun signOut() {
        userRepository.signOut()
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text

    fun loadUserInfo() {
        viewModelScope.launch {
            val result = userRepository.loadUserInfo()
            result.let { resourceRemote ->
                when (resourceRemote) {
                    is ResourceRemote.Success -> {
                        result.data?.documents?.forEach{document ->
                            val user = document.toObject<User>()
                            if(user?.uid == userRepository.getUIDCurrentUser()){
                                _userLoaded.postValue(user)
                            }
                        }
                    }
                    is ResourceRemote.Error -> {
                        val msg = result.message
                        _errorMsg.postValue(msg)
                    }
                    else -> {

                    }
                }
            }


        }

    }
}