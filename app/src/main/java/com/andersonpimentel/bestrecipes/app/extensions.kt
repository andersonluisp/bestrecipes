package com.andersonpimentel.bestrecipes.app

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

fun Int.print(): String {
    return App.instance.getString(this)
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun ImageView.load(url: String?) {
    url?.let {
        Glide.with(context).load(it).into(this)
    }
}
