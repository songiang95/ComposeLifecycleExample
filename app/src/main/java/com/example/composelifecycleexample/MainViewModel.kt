package com.example.composelifecycleexample

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class MainViewModel : ViewModel() {

    var url by mutableStateOf("")

    fun changeUrl(url: String) {
        this.url = url
    }

    private val _data = MutableLiveData<List<Int>>(listOf(8,7,5,3,1,6,4,2,9, 10, 0))
    val data: LiveData<List<Int>> get() = _data

    fun addData() {
        _data.value = _data.value?.toMutableList()?.apply {
            add(Random.nextInt(10))
        }
    } fun addDataToTop() {
        _data.value = _data.value?.toMutableList()?.apply {
            add(0,Random.nextInt(10))
        }
    }

}