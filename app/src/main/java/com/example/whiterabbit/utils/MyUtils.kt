package com.example.whiterabbit.utils

import android.view.View
import com.example.whiterabbit.R
import com.google.android.material.snackbar.Snackbar


fun View.showSnackBar(message: String){
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).also { snackbar ->
        snackbar.setAction("Ok") {
            snackbar.dismiss()
        }.setActionTextColor(resources.getColor(R.color.colorPrimaryDark))
    }.show()
}