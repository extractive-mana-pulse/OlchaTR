package com.example.olchatr

import androidx.compose.ui.window.ComposeUIViewController
import com.example.olchatr.core.di.appModule
import com.example.olchatr.core.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin {
            modules(iosModule, appModule)
        }
    }
) {
    App()
}