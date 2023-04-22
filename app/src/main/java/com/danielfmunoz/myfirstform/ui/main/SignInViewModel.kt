package com.danielfmunoz.myfirstform.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danielfmunoz.myfirstform.ui.data.ResourceRemote
import com.danielfmunoz.myfirstform.ui.data.UserRepository
import kotlinx.coroutines.launch

class SignInViewModel : ViewModel() {

    private val userRepository = UserRepository()

    private val _errorMsg: MutableLiveData<String> = MutableLiveData()
    val errorMsg: LiveData<String> = _errorMsg

    private val _isSuccessSignIn: MutableLiveData<Boolean> = MutableLiveData()
    val isSuccessSignIn: LiveData<Boolean> = _isSuccessSignIn

    fun validateFields(email: String, password: String) {

        if (email.isEmpty() || password.isEmpty()) {
            _errorMsg.value = "Debe digitar todos los campos"
        } else {
            viewModelScope.launch {
                val resourceRemote = userRepository.signInUser(email, password)
                when (resourceRemote) {
                    is ResourceRemote.Success -> {
                        _isSuccessSignIn.postValue(true)
                    }

                    is ResourceRemote.Error -> {
                        var msg = resourceRemote.message
                        when (resourceRemote.message) {
                            "A network error (such as timeout, interrupted connection or unreachable host) has occurred." -> msg =
                                "Valida tu conexion a internet."

                            "The password is invalid or the user does not have a password." -> msg =
                                "Usurio o correo incorrectos."

                            "The email address is badly formatted." -> msg =
                                "El correo tiene un formato invalido."
                        }
                        _errorMsg.postValue(msg)
                    }

                    else -> {

                    }
                }
            }
        }

    }
}