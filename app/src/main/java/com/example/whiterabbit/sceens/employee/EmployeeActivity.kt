package com.example.whiterabbit.sceens.employee

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.whiterabbit.R
import com.example.whiterabbit.data.database.entity.EmployeeResponseItem
import kotlinx.android.synthetic.main.activity_employee.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class EmployeeActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()
    private val factory by instance<EmployeeViewModelFactory>()
    private lateinit var viewModel: EmployeeViewModel
    private var empId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee)

        empId = intent.getIntExtra("EMP_ID", 0)
        viewModel = ViewModelProvider(this, factory).get(EmployeeViewModel::class.java)
        viewModel.getEmployeeById(empId!!).observe(this, Observer {
            initUi(it)
        })
    }

    private fun initUi(emloyee: EmployeeResponseItem){
        val options: RequestOptions = RequestOptions()
            .centerCrop()
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)

        Glide.with(this.applicationContext).load(emloyee.profile_image).apply(options).into(profile_image)

        employee_tv.text = emloyee.name
    }
}
