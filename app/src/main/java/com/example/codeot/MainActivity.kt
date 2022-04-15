package com.example.codeot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webViewSetup()
    }

    private fun webViewSetup() {
        val html: String = """
            <html>
                <head>
                    <title>Hello World</title>
                </head>
                <body>
                    <textarea id="source"></textarea>
                    <script type="text/javascript">
                        let msg;
                        const source = document.getElementById("source");
                        
                        source.addEventListener("input", handleTextChange);
                        
                        function handleTextChange(e) {
                            Android.handleTextChange(e.target.value)
                        }
                    </script>
                </body>
            </html>
        """.trimIndent()

        var message: String = ""
        val myWebApp = WebAppInterface(this)
        myWebApp.onTextChange { msg -> message = msg }

        val  myWebView: WebView = findViewById(R.id.my_webView)
        myWebView.webViewClient = WebViewClient()
        myWebView.settings.javaScriptEnabled = true
        myWebView.addJavascriptInterface(myWebApp, "Android")
        myWebView.loadData(html, "text/html", "utf-8")

        val myButton: Button = findViewById(R.id.my_button)
        myButton.setOnClickListener { Toast.makeText(this, message, Toast.LENGTH_LONG).show() }
    }
}