package com.example.whiterabbit.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.whiterabbit.data.database.entity.EmployeeResponseItem

@Dao
interface EmployeeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(employees: List<EmployeeResponseItem>)
    //Whoola, Upsert simply means Update and insert here :-)

    @Query("SELECT * FROM EmployeeResponseItem")
    fun getEmployees() : LiveData<List<EmployeeResponseItem>>

    @Query("SELECT * FROM EmployeeResponseItem WHERE id=:employeeId")
    fun getEmployeeById(employeeId: Int) : LiveData<EmployeeResponseItem>

}