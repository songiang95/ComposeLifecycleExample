package com.example.composelifecycleexample

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.random.Random

@Composable
fun SkippedScreenStateHolder() {
    val datas = remember {
        mutableStateListOf<MyData1>()
    }

    LogCompositions(tag = "abba", msg = "SkippedScreenStateHolder")

    Column(Modifier.fillMaxSize()) {
        Row {
            Button(onClick = { datas.addToBottom() }) {
                Text(text = "Add to bottom")
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(onClick = { datas.addToMiddle() }) {
                Text(text = "Add to middle")
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(onClick = { datas.addToTop() }) {
                Text(text = "Add to top")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        ColorScreen(items = datas)
    }
}

@Composable
fun ColorScreen(items: List<MyData1>) {
    LogCompositions(tag = "abba", msg = "ColorScreen")
    Column {
        items.forEach { data ->
            key(data.id) {
                ColorRow(data = data)
            }
        }
    }
}

@Composable
fun ColorRow(data: MyData1) {
    LogCompositions(tag = "abba", msg = "ColorRow: ${data.id}")
    Text(
        text = data.id.toString(),
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
fun StablePreview() {
    SkippedScreenStateHolder()
}

private fun SnapshotStateList<MyData1>.addToBottom() {
    this.add(MyData1())
}

private fun SnapshotStateList<MyData1>.addToTop() {
    this.add(0, MyData1())
}

private fun SnapshotStateList<MyData1>.addToMiddle() {
    this.add(this.size / 2, MyData1())
}

private fun getRandomColor(): Color {
    val red = Random.nextInt(0, 255)
    val green = Random.nextInt(0, 255)
    val blue = Random.nextInt(0, 255)
    return Color(red = red, green = green, blue = blue, alpha = 255)
}