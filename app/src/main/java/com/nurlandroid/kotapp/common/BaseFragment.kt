package com.nurlandroid.kotapp.common

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

abstract class BaseFragment(val layout: Int) : Fragment(layout) {

    private lateinit var progressDialog: ProgressFragmentDialog
    private lateinit var errorDialog: Dialog

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUI()
    }

    protected open fun setUI() {
        progressDialog = DialogFactory.getProgressDialog()
        errorDialog = DialogFactory.getErrorDialog(requireContext())
        closeProgress()
        closeError()
    }

    protected open fun showProgress() {
        if (!progressDialog.isResumed) {
            progressDialog.show(childFragmentManager, "")
        }
    }

    protected fun closeProgress() {
        if (progressDialog.isVisible) {
            progressDialog.dismiss()
        }
    }

    protected open fun showError() {
        if (!errorDialog.isShowing) {
            closeProgress()
            errorDialog.show()
        }
    }

    protected open fun closeError() {
        if (!errorDialog.isShowing) {
            errorDialog.dismiss()
        }
    }
}