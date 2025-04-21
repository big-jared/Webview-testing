package com.example.myapplication

import android.app.Activity
import android.os.Bundle
import android.webkit.ConsoleMessage
import android.webkit.WebView
import android.webkit.WebViewClient
import android.webkit.WebChromeClient

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val webView = findViewById<WebView>(R.id.webView)

        // Enable JavaScript and DOM storage
        webView.settings.javaScriptEnabled = true

        // Enable debugging for WebView

        // Settings important for iframes and autofill

        // Set WebViewClient to handle page navigation
        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                // Log when the page has finished loading
                println("Page loaded: $url")
            }
        }

        // Set WebChromeClient to handle JavaScript dialogs and console logs
        webView.webChromeClient = object : WebChromeClient() {
            override fun onConsoleMessage(consoleMessage: ConsoleMessage?): Boolean {
                consoleMessage?.let {
                    println("Console: ${it.message()} [${it.sourceId()}:${it.lineNumber()}]")
                }
                return true
            }
        }

        // Load the test HTML file from assets
        webView.loadUrl("file:///android_asset/index.html")
    }
}