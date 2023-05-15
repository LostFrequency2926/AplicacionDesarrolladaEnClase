package com.danielfmunoz.myfirstform.ui.newSerie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danielfmunoz.myfirstform.ui.data.ResourceRemote
import com.danielfmunoz.myfirstform.ui.data.SerieRepository
import com.danielfmunoz.myfirstform.ui.model.Serie
import kotlinx.coroutines.launch

class NewSerieViewModel : ViewModel() {

    private val serieRepository = SerieRepository()

    private val _errorMsg: MutableLiveData<String?> = MutableLiveData()
    val errorMsg: LiveData<String?> = _errorMsg

    private val _createSerieSucces: MutableLiveData<String?> = MutableLiveData()
    val createSerieSucces: LiveData<String?> = _createSerieSucces

    fun validateData(name: String,
                     season: String,
                     summary: String,
                     score: String,
                     isTerrorSelected: Boolean,
                     isSuspenseSelected: Boolean,
                     isDramaSelected: Boolean,
                     isFiccionSelected: Boolean) {

        if(name.isEmpty() || season.isEmpty() || summary.isEmpty() || score.isEmpty()){
            _errorMsg.value = "Debe registrar todos los campos"
        }else{
            viewModelScope.launch {
                val serie = Serie (
                    name = name,
                    season = season,
                    summary =  summary,
                    score =  score,
                    isTerrorSelected = isTerrorSelected,
                    isSuspenseSelected =  isSuspenseSelected,
                    isDramaSelected =  isDramaSelected,
                    isFiccionSelected = isFiccionSelected)

                val result = serieRepository.saveSerie(serie)

                result.let { resourceRemote ->
                    when(resourceRemote){
                        is ResourceRemote.Success -> {
                            _errorMsg.postValue("Serie Alamacenada")
                            _createSerieSucces.postValue(resourceRemote.data)
                        }
                        is ResourceRemote.Error -> {
                            val msg = resourceRemote.message
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