package com.example.whiterabbit

import android.app.Application
import com.example.whiterabbit.data.database.AppDatabase
import com.example.whiterabbit.data.network.ApiInterface
import com.example.whiterabbit.data.network.NetworkConnectionInterceptor
import com.example.whiterabbit.data.repository.EmployeeRepo
import com.example.whiterabbit.sceens.employee.EmployeeViewModelFactory
import com.example.whiterabbit.sceens.home.HomeViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class RabbitApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@RabbitApplication))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { ApiInterface(instance()) }
        bind() from singleton { AppDatabase(instance()) }

        bind() from singleton { EmployeeRepo(instance(), instance()) }
        bind() from provider { HomeViewModelFactory(instance()) }
        bind() from provider { EmployeeViewModelFactory(instance()) }
    }
}