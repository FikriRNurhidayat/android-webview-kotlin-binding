package com.example.codeot

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.widget.Button
import android.widget.Toast
import androidx.webkit.WebViewAssetLoader
import com.example.codeot.web.Client

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myWebLoader = WebViewAssetLoader.Builder()
            .addPathHandler("/assets/", WebViewAssetLoader.AssetsPathHandler(this))
            .build()

        var message = ""
        val myWebApp = Codeot(this)
        myWebApp.onTextChange { msg -> message = msg }

        val  myWebView: WebView = findViewById(R.id.my_webView)
        myWebView.webViewClient = Client(myWebLoader)
        myWebView.settings.javaScriptEnabled = true
        myWebView.settings.allowFileAccess = true
        myWebView.addJavascriptInterface(myWebApp, "Android")
        myWebView.loadUrl("file:///android_asset/index.html")

        val myButton: Button = findViewById(R.id.my_button)
        myButton.setOnClickListener { Toast.makeText(this, message, Toast.LENGTH_LONG).show() }
    }
}