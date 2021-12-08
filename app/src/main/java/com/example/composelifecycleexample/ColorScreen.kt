package com.example.composelifecycleexample

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlin.random.Random

@Composable
fun ColorScreenStateHolder() {
    val numbers = remember {
        mutableStateListOf<Long>()
    }

    Column {
        Row {
            Button(onClick = { numbers.addToBottom() }) {
                Text(text = "Add to bottom")
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(onClick = { numbers.addToMiddle() }) {
                Text(text = "Add to middle")
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(onClick = { numbers.addToTop() }) {
                Text(text = "Add to top")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        ColorScreen(numbers = numbers)
    }
}

@Composable
fun ColorScreen(numbers: List<Long>) {
    Column {
        numbers.forEach { id ->
            ColorRow(id = id)
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