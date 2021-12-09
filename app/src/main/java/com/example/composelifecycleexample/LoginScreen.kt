package com.example.composelifecycleexample

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LoginScreenStateHolder() {
    var error by remember {
        mutableStateOf(false)
    }
    LogCompositions(tag = "abba", msg = "LoginScreenStateHolder")

    Column(modifier = Modifier.fillMaxSize()) {
        Button(onClick = { error = !error }) {
            Text(text = "Change Error")
        }
        LoginScreen(showError = error)
    }
    Log.d("abba", "--------------------------------------------------------------------------")
}

@Composable
fun LoginScreen(showError: Boolean) {
    LogCompositions(tag = "abba", msg = "LoginScreen")

    if (showError) {
        LoginError()
    }
    LoginInput()
}

@Composable
fun LoginError() {
    LogCompositions(tag = "abba", msg = "LoginError")
    Text(text = "Login Error", style = MaterialTheme.typography.h2)
}

@Composable
fun LoginInput() {
    LogCompositions(tag = "abba", msg = "LoginInput")
    Text(text = "Login Input", style = MaterialTheme.typography.h2)
}

@Preview
@Composable
fun LoginScreenPreview() {
    Surface(color = Color.White) {
        LoginScreenStateHolder()
    }
}