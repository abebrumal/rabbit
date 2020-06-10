package com.example.whiterabbit.sceens.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.whiterabbit.R
import com.example.whiterabbit.data.database.entity.EmployeeResponseItem
import com.example.whiterabbit.sceens.employee.EmployeeActivity
import com.example.whiterabbit.utils.showSnackBar
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class HomeActivity : AppCompatActivity(), KodeinAware, HomeListener, EmployeeClickListener {

    override val kodein by kodein()

    private val factory by instance<HomeViewModelFactory>()
    private lateinit var viewModel: HomeViewModel
    private var employees: List<EmployeeResponseItem>  = ArrayList()
    private lateinit var adapter: EmployeeRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecycler(employees)

        initData()

    }

    private fun initData(){
        viewModel = ViewModelProvider(this, factory).get(HomeViewModel::class.java)
        viewModel.listener = this

        viewModel.getEmployeesFromLocal().observe(this, Observer {
            if (it.isNotEmpty()){
                (employees as ArrayList).clear()
                (employees as ArrayList).addAll(it)
                adapter.updateEmployeesList(it)
            }else {
                viewModel.getEmployees()
            }
        })
    }

    private fun initRecycler(employees: List<EmployeeResponseItem>){
        adapter = EmployeeRecyclerAdapter(employees, this.applicationContext, this)
        employee_rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        employee_rv.adapter = adapter
    }

    override fun showProgress() {
        progress_employee.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress_employee.visibility = View.GONE
    }

    override fun getEmployeeSuccess(employees: List<EmployeeResponseItem>) {
        (this.employees as ArrayList).clear()
        (this.employees as ArrayList).addAll(employees)
        adapter.updateEmployeesList(employees)
    }

    override fun onApiError(error: String) {
        root_layout_home.showSnackBar(error)
    }

    override fun onEmployeeClicked(employeeId: Int) {
        val intent = Intent(this, EmployeeActivity::class.java)
        intent.putExtra("EMP_ID", employeeId)
        startActivity(intent)
    }
}

