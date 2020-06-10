package com.example.whiterabbit.data.database

import androidx.room.TypeConverter
import com.example.whiterabbit.data.database.entity.EmployeeResponseItem
import com.example.whiterabbit.data.responses.Address
import com.example.whiterabbit.data.responses.Company
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromEmployeeList(value: List<EmployeeResponseItem>): String {
        val gson = Gson()
        val type = object : TypeToken<List<EmployeeResponseItem>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toEmployeeList(value: String): List<EmployeeResponseItem> {
        val gson = Gson()
        val type = object : TypeToken<List<EmployeeResponseItem>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromAddress(value: Address): String {
        val gson = Gson()
        val type = object : TypeToken<Address>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toAddress(value: String): Address {
        val gson = Gson()
        val type = object : TypeToken<Address>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromCommpany(value: Company): String {
        val gson = Gson()
        val type = object : TypeToken<Company>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toCompany(value: String): Company {
        val gson = Gson()
        val type = object : TypeToken<Company>() {}.type
        return gson.fromJson(value, type)
    }
}