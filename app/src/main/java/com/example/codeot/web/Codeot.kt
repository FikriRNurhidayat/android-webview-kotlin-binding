package com.example.codeot

import android.content.Context
import android.webkit.JavascriptInterface
import android.widget.Toast

typealias TextChangeListener = (String) -> Unit

class Codeot(private val mContext: Context) {
    private lateinit var onTextChangeListener: TextChangeListener

    @JavascriptInterface
    fun showToast(toast: String) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show()
    }

    fun onTextChange(fn: TextChangeListener) {
        onTextChangeListener = fn
    }

    @JavascriptInterface
    fun handleTextChange(msg: String) {
        onTextChangeListener(msg)
    }
}
