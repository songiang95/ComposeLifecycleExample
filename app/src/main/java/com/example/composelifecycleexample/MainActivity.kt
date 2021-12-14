package com.example.composelifecycleexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composelifecycleexample.ui.theme.ComposeLifecycleExampleTheme

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeLifecycleExampleTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Main(viewModel)
                }
            }
        }
    }
}


@Composable
fun TestState(viewModel: MainViewModel) {
    Button(onClick = {
        viewModel.changeUrl("ABCCC")
    }) {
        TestView(url = viewModel.url)
    }
}

@Composable
fun TestView(url: String) {
    Text(text = url)
}

private var screenId by mutableStateOf(ScreenID.Main)

@Composable
fun Main(viewModel: MainViewModel) {
    LogCompositions(tag = "abba", msg = "Main")
    Column {
        BackHandler {
            screenId = ScreenID.Main
        }

        when (screenId) {
            ScreenID.Main -> {
                MainScreen()
            }

            ScreenID.Login -> {
                LoginScreenStateHolder()
            }

            ScreenID.Color -> {
                ColorScreenStateHolder()
            }

            ScreenID.Skipped -> {
                SkippedScreenStateHolder(viewModel)
            }
        }
    }
}

@Composable
fun MainScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { screenId = ScreenID.Login }) {
            Text(text = "Login Screen")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { screenId = ScreenID.Color }) {
            Text(text = "Color Screen")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { screenId = ScreenID.Skipped }) {
            Text(text = "Skipped Screen")
        }
    }
}


enum class ScreenID {
    Main, Login, Color, Skipped
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeLifecycleExampleTheme {
        Main(viewModel = MainViewModel())
    }
}