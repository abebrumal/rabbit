package com.example.whiterabbit.sceens.home

import androidx.lifecycle.ViewModel
import com.example.whiterabbit.data.network.CustomApiException
import com.example.whiterabbit.data.network.NoInternetException
import com.example.whiterabbit.data.repository.EmployeeRepo
import com.example.whiterabbit.utils.Coroutines

class HomeViewModel(
    private val repository: EmployeeRepo
) : ViewModel(){

    var listener: HomeListener? = null

    fun getEmployees() {
        Coroutines.main {
            try {
                listener?.showProgress()
                val response = repository.getEmployees()
                repository.upsertEmployees(response)
                listener?.getEmployeeSuccess(response)
                listener?.hideProgress()
            }catch (e: CustomApiException){
                listener?.hideProgress()
                listener?.onApiError(e.message!!)
            }catch (e : NoInternetException){
                listener?.hideProgress()
                listener?.onApiError(e.message!!)
            }
        }
    }

    fun getEmployeesFromLocal() = repository.getEmployeesFromDB()
}