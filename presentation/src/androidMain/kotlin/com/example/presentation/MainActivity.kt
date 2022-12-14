package com.example.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.arkivanov.decompose.defaultComponentContext
import com.example.presentation.root.ExampleApp
import com.example.presentation.root.RootComponentImpl

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val rootComponent = RootComponentImpl(defaultComponentContext())
        setContent {
            ExampleApp(rootComponent)
        }
    }
}