package com.danielfmunoz.myfirstform.ui.bottomMenu.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.danielfmunoz.myfirstform.ui.data.UserRepository

class MyProfileViewModel : ViewModel() {

    private var userRepository = UserRepository()

    fun signOut() {
        userRepository.signOut()
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text
}