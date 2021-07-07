package com.luisgl.finerioapp.ui.utils

import android.app.AlertDialog
import android.content.Context
import android.view.View
import android.widget.ProgressBar

fun ProgressBar.show() {
    visibility = View.VISIBLE
}

fun ProgressBar.hide() {
    visibility = View.GONE
}

fun Context.showAlert(title: String, message: String) {
    val builder = AlertDialog.Builder(this)
    builder.setTitle(title)
    builder.setMessage(message)
    builder.show()
}