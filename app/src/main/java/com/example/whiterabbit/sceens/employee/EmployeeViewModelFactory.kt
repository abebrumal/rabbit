package com.example.whiterabbit.sceens.employee

import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.whiterabbit.data.repository.EmployeeRepo

@Suppress("UNCHECKED_CAST")
class EmployeeViewModelFactory(
    private val repository: EmployeeRepo
) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EmployeeViewModel(repository) as T
    }
}