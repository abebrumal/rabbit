package com.example.whiterabbit.data.database

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.example.whiterabbit.data.database.dao.EmployeeDao
import com.example.whiterabbit.data.database.entity.EmployeeResponseItem

@Database(
    entities =[EmployeeResponseItem::class],
    version = 1
)

@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase(){

    abstract fun getEmployeeDao() : EmployeeDao

    companion object{

        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance?: buildDataBase(context).also {
                instance = it
            }
        }


        private fun buildDataBase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "RabbitDatabase")
                .build()
    }


}