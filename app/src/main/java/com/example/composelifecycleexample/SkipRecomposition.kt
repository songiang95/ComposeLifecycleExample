package com.example.composelifecycleexample

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
fun SkippedScreenStateHolder() {
    val colors = remember {
        mutableStateListOf<MyColor>()
    }

    LogCompositions(tag = "abba", msg = "SkippedScreenStateHolder")

    Column {
        Row {
            Button(onClick = { colors.addToBottom() }) {
                Text(text = "Add to bottom")
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(onClick = { colors.addToMiddle() }) {
                Text(text = "Add to middle")
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(onClick = { colors.addToTop() }) {
                Text(text = "Add to top")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        ColorScreen(items = colors)
    }
}

@Composable
fun ColorScreen(items: List<MyColor>) {
    LogCompositions(tag = "abba", msg = "ColorScreen")
    Column {
        items.forEach { myColor ->
            ColorRow(myColor = myColor)
        }
    }
}

@Composable
fun ColorRow(myColor: MyColor) {
    LogCompositions(tag = "abba", msg = "ColorRow: ${myColor.id}")
    Text(
        text = myColor.id.toString(),
        color = Color.White,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(vertical = 5.dp, horizontal = 16.dp)
            .background(getRandomColor())
            .wrapContentSize()
    )

}

private fun SnapshotStateList<MyColor>.addToBottom() {
    this.add(MyColor())
}

private fun SnapshotStateList<MyColor>.addToTop() {
    this.add(0, MyColor())
}

private fun SnapshotStateList<MyColor>.addToMiddle() {
    this.add(this.size / 2, MyColor())
}

private fun getRandomColor(): Color {
    val red = Random.nextInt(0, 255)
    val green = Random.nextInt(0, 255)
    val blue = Random.nextInt(0, 255)
    return Color(red = red, green = green, blue = blue, alpha = 255)
}