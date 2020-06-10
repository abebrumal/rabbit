package com.example.whiterabbit.sceens.home

import com.example.whiterabbit.data.database.entity.EmployeeResponseItem

interface HomeListener {

    fun showProgress()

    fun hideProgress()

    fun getEmployeeSuccess(employees: List<EmployeeResponseItem>)

    fun onApiError(error: String)
}