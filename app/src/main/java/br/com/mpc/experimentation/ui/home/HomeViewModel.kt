package br.com.mpc.experimentation.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class HomeViewModel(private val handle: SavedStateHandle) : ViewModel() {
    private var count: Int = 0
    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    fun changeText() {
        count ++
        if (count == 1) _text.value = "Texto alterado 1 vez"
        else _text.value = "Texto alterado $count vezes"
    }
}