package com.iesam.viewtapasapp.app.extensions

import android.view.View

fun View.hide(){
    this.visibility = View.GONE
}

fun View.visible(){
    this.visibility = View.VISIBLE
}