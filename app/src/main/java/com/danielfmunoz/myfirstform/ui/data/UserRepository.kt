package com.danielfmunoz.myfirstform.ui.data;

import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await


class UserRepository {

    private var auth: FirebaseAuth = Firebase.auth

    suspend fun signUpUser(correo: String, password: String): ResourceRemote<String?> {
        return try {
            val result = auth.createUserWithEmailAndPassword(correo, password).await()
            ResourceRemote.Success(data = result.user?.uid)
        } catch (e: FirebaseAuthException) {
            ResourceRemote.Error(message = e.localizedMessage)
        } catch (e: FirebaseNetworkException) {
            ResourceRemote.Error(message = e.localizedMessage)
        }
    }

    suspend fun signInUser(correo: String, password: String): ResourceRemote<String?> {
        return try {
            val result = auth.signInWithEmailAndPassword(correo, password).await()
            ResourceRemote.Success(data = result.user?.uid)
        } catch (e: FirebaseAuthException) {
            ResourceRemote.Error(message = e.localizedMessage)
        } catch (e: FirebaseNetworkException) {
            ResourceRemote.Error(message = e.localizedMessage)
        }
    }

    fun isSessionActive() : Boolean{

        return auth.currentUser != null
    }

    fun signOut() {
        auth.signOut()
    }


}
