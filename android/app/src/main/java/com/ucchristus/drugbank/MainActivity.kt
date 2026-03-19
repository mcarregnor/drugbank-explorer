package com.ucchristus.drugbank

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat

class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Edge-to-edge (status bar transparent)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        webView = WebView(this)
        setContentView(webView)

        webView.settings.apply {
            javaScriptEnabled    = true
            domStorageEnabled    = true
            allowFileAccess      = true
            mixedContentMode     = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            cacheMode            = WebSettings.LOAD_DEFAULT
            loadsImagesAutomatically = true
            useWideViewPort      = true
            loadWithOverviewMode = true
            setSupportZoom(false)
            builtInZoomControls  = false
        }

        webView.webChromeClient = WebChromeClient()

        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView, request: WebResourceRequest
            ): Boolean {
                // Allow all URLs (needed for PubChem/RxNorm/OpenFDA API calls)
                return false
            }
        }

        // Load from assets — restore state on rotation
        if (savedInstanceState != null) {
            webView.restoreState(savedInstanceState)
        } else {
            webView.loadUrl("file:///android_asset/index.html")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        webView.saveState(outState)
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}
