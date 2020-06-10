package com.example.whiterabbit.sceens.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.whiterabbit.R
import com.example.whiterabbit.data.database.entity.EmployeeResponseItem
import kotlinx.android.synthetic.main.layout_employee_rv_single.view.*

class EmployeeRecyclerAdapter(
    private val employees: List<EmployeeResponseItem>,
    private val context: Context,
    private val employeeClickListener: EmployeeClickListener
) : RecyclerView.Adapter<EmployeeRecyclerAdapter.EmployeeViewHolder>(){

    inner class EmployeeViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val employeeTv = view.employee_tv
        val employeeIv = view.employee_iv
        val companyTv = view.company_tv
        val emailTv = view.email_tv
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        return EmployeeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_employee_rv_single, parent, false))
    }

    override fun getItemCount() = employees.size

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        val options: RequestOptions = RequestOptions()
            .centerCrop()
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)

        Glide.with(context).load(employees[position].profile_image).apply(options).into(holder.employeeIv)
        holder.employeeTv.text = employees[position].name
        //holder.companyTv.text = employees[position].company?.name
        holder.emailTv.text = employees[position].email

        holder.itemView.setOnClickListener {
            employeeClickListener.onEmployeeClicked(employees[position].id)
        }
    }

    fun updateEmployeesList(employees: List<EmployeeResponseItem>){
        (this.employees as ArrayList).clear()
        this.employees.addAll(employees)
        notifyDataSetChanged()
    }
}

interface EmployeeClickListener{
    fun onEmployeeClicked(employeeId: Int)
}