package com.example.presentation.extentions

import android.view.View

fun View.hide() {
    this.visibility = View.INVISIBLE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.disappear() {
    this.visibility = View.GONE
}