package com.example.whiterabbit.data.repository

import com.example.whiterabbit.data.database.AppDatabase
import com.example.whiterabbit.data.database.entity.EmployeeResponseItem
import com.example.whiterabbit.data.network.ApiInterface
import com.example.whiterabbit.data.network.SafeApiRequest
import com.example.whiterabbit.data.responses.EmployeeResponse

class EmployeeRepo(
    private val apiInterface: ApiInterface,
    private val appDatabase: AppDatabase
): SafeApiRequest() {

    suspend fun getEmployees() : EmployeeResponse = apiRequest {
        apiInterface.getEmployees()
    }

    suspend fun upsertEmployees(employees: List<EmployeeResponseItem>) = appDatabase.getEmployeeDao().upsert(employees)

    fun getEmployeesFromDB() = appDatabase.getEmployeeDao().getEmployees()

    fun getEmployeeById(employeeId: Int) = appDatabase.getEmployeeDao().getEmployeeById(employeeId)
}