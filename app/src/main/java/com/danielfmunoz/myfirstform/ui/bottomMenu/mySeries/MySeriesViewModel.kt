package com.danielfmunoz.myfirstform.ui.bottomMenu.mySeries

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danielfmunoz.myfirstform.ui.data.ResourceRemote
import com.danielfmunoz.myfirstform.ui.data.SerieRepository
import com.danielfmunoz.myfirstform.ui.model.Serie
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.launch

class MySeriesViewModel : ViewModel() {

    private val serieRepository = SerieRepository()

    private var serieList: ArrayList<Serie> = ArrayList()

    private  val _seriesList: MutableLiveData<ArrayList<Serie>> = MutableLiveData()
    val seriesList: LiveData<ArrayList<Serie>> = _seriesList

    private val _errorMsg: MutableLiveData<String?> = MutableLiveData()
    val errorMsg: LiveData<String?> = _errorMsg

    fun loadSeries() {

        serieList.clear()
        viewModelScope.launch {
            val result = serieRepository.loadSeries()
            result.let { resourceRemote ->
                when (resourceRemote){
                    is ResourceRemote.Success -> {
                        resourceRemote.data?.documents?.forEach { document ->
                            val serie = document.toObject<Serie>()
                            serie?.let { serieList.add(it) }
                        }
                        _seriesList.postValue(serieList)
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