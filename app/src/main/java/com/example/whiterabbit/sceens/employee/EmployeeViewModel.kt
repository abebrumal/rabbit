package com.example.whiterabbit.sceens.employee

import androidx.lifecycle.ViewModel
import com.example.whiterabbit.data.repository.EmployeeRepo

class EmployeeViewModel(
   private val repository: EmployeeRepo
) : ViewModel(){

    fun getEmployeeById(id: Int) = repository.getEmployeeById(id)
}