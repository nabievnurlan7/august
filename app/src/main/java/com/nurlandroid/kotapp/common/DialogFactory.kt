package com.nurlandroid.kotapp.common

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import com.nurlandroid.kotapp.R


object DialogFactory {

    fun getProgressDialog(): ProgressFragmentDialog = ProgressFragmentDialog()

    fun getErrorDialog(context: Context): Dialog =
        AlertDialog.Builder(context).setCancelable(true).setView(R.layout.dialog_error).create()
}