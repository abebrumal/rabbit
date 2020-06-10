package com.example.whiterabbit.sceens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.whiterabbit.data.repository.EmployeeRepo

@Suppress("UNCHECKED_CAST")
class HomeViewModelFactory(
    private val repository: EmployeeRepo
) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(repository) as T
    }
}