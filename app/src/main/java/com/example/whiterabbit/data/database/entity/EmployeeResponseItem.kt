package com.example.whiterabbit.data.database.entity

import androidx.annotation.Nullable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.whiterabbit.data.responses.Address
import com.example.whiterabbit.data.responses.Company

@Entity
data class EmployeeResponseItem(
    val address: Address?,
    val email: String ?,
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String ?,
    val profile_image: String ?,
    val username: String?
)