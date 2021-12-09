package com.example.composelifecycleexample

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.random.Random

@Composable
fun ColorScreenStateHolder() {
    val items = remember {
        mutableStateListOf<Long>()
    }

    Column(Modifier.fillMaxSize()) {
        Row {
            Button(onClick = { items.addToBottom() }) {
                Text(text = "Add to bottom")
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(onClick = { items.addToMiddle() }) {
                Text(text = "Add to middle")
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(onClick = { items.addToTop() }) {
                Text(text = "Add to top")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        ColorScreen(items = items)
    }
}

@Composable
fun ColorScreen(items: List<Long>) {
    Column {
        items.forEach { id ->
            ColorRow(id = id)

            /*   key(id) {
                   ColorRow(id = id)
               }*/
        }
    }
}

@Composable
fun ColorRow(id: Long) {
    Text(
        text = id.toString(),
        color = Color.White,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(vertical = 5.dp, horizontal = 16.dp)
            .background(getRandomColor())
            .wrapContentSize()
    )
}

@Preview
@Composable
fun ColorScreenPreview() {
    Surface(color = Color.White) {
        ColorScreenStateHolder()
    }
}

private fun SnapshotStateList<Long>.addToBottom() {
    this.add(System.currentTimeMillis())
}

private fun SnapshotStateList<Long>.addToTop() {
    this.add(0, System.currentTimeMillis())
}

private fun SnapshotStateList<Long>.addToMiddle() {
    val addPosition = this.size / 2
    this.add(addPosition, System.currentTimeMillis())
}

private fun getRandomColor(): Color {
    val red = Random.nextInt(0, 255)
    val green = Random.nextInt(0, 255)
    val blue = Random.nextInt(0, 255)
    return Color(red = red, green = green, blue = blue, alpha = 255)
}