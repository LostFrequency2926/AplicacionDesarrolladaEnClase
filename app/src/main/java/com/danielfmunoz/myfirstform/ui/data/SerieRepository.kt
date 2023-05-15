package com.danielfmunoz.myfirstform.ui.data

import com.danielfmunoz.myfirstform.ui.model.Serie
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class SerieRepository {

    private var db = Firebase.firestore
    private var auth : FirebaseAuth = Firebase.auth

    suspend fun saveSerie(serie: Serie): ResourceRemote<String?> {
        return try {
            val uid = auth.currentUser?.uid
            val path = uid?.let { db.collection("users").document(it).collection("series")}
            val documentSerie = path?.document()

            serie.id = documentSerie?.id
            serie.id?.let { path?.document(it)?.set(serie)?.await() }

            ResourceRemote.Success(data = uid)
        } catch (e: FirebaseFirestoreException) {
            ResourceRemote.Error(message = e.localizedMessage)
        } catch (e: FirebaseNetworkException) {
            ResourceRemote.Error(message = e.localizedMessage)
        }

    }

    suspend fun loadSeries(): ResourceRemote<QuerySnapshot?> {
        return try {
            val docRef = auth.uid?.let{db.collection("users").document(it).collection("series")}
            val result = docRef?.get()?.await()
            ResourceRemote.Success(data = result)
        } catch (e: FirebaseAuthException) {
            ResourceRemote.Error(message = e.localizedMessage)
        } catch (e: FirebaseNetworkException) {
            ResourceRemote.Error(message = e.localizedMessage)
        }
    }


}