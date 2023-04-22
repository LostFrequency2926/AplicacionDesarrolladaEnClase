package com.danielfmunoz.myfirstform.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danielfmunoz.myfirstform.ui.data.ResourceRemote
import com.danielfmunoz.myfirstform.ui.data.UserRepository
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {

    private val userRepository = UserRepository()

    private val _errorMsg: MutableLiveData<String> = MutableLiveData()
    val errorMsg: LiveData<String> = _errorMsg

    private val _isSuccessSignUp: MutableLiveData<Boolean> = MutableLiveData()
    val isSuccessSignUp: LiveData<Boolean> = _isSuccessSignUp

    fun validateFields(correo: String, password: String, repetirPassword: String) {

        if (correo.isEmpty() || password.isEmpty() || repetirPassword.isEmpty()) {
            _errorMsg.value = "Debe digitar todos los campos"
        } else {
            if (password.length < 6) {
                _errorMsg.value = "La contraseña debe tener minimo 6 caracteres"
            } else {
                if (password != repetirPassword) {
                    _errorMsg.value = "Las contraseñas no coinciden"
                } else {
                    viewModelScope.launch {
                        val result = userRepository.signUpUser(correo, password)
                        result.let { resourceRemote ->
                            when (resourceRemote) {
                                is ResourceRemote.Success -> {
                                    _isSuccessSignUp.postValue(true)
                                }

                                is ResourceRemote.Error -> {
                                    var msg = resourceRemote.message
                                    when (resourceRemote.message) {
                                        "A network error (such as timeout, interrupted connection or unreachable host) has occurred." -> msg =
                                            "Valida tu conexion a internet"

                                        "The email address is already in use by another account." -> msg =
                                            "Este correo ya esta en uso"

                                        "The email address is badly formatted." -> msg =
                                            "El correo tiene un formato invalido "
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

        }
    }
}