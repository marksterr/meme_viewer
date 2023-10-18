package com.example.basicapplication.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.basicapplication.data.IRepository
import com.example.basicapplication.data.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MemeViewModel @Inject constructor(private val repository: IRepository):
    ViewModel() {

    private val _data: MutableLiveData<UIState> = MutableLiveData()
    val data: LiveData<UIState>
        get() = _data

    init {
        viewModelScope.launch {
            repository.getMemes().collect {
                _data.value = it
            }
        }
    }
}