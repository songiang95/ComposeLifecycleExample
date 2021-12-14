package com.example.composelifecycleexample

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.VerticalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
fun SkippedScreenStateHolder(viewModel: MainViewModel) {
    val datas = remember {
        mutableStateListOf<MyData2>()
    }

    LogCompositions(tag = "abba", msg = "SkippedScreenStateHolder")

    Column(Modifier.fillMaxSize()) {
        Row {
            Button(onClick = { viewModel.addData() }) {
                Text(text = "Add to bottom")
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(onClick = { datas.addToMiddle() }) {
                Text(text = "Add to middle")
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(onClick = { viewModel.addDataToTop() }) {
                Text(text = "Add to top")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        PagerView(viewModel = viewModel, Random.nextInt(6))

//        ColorScreen(items = datas)
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalPagerApi::class)
@Composable
fun PagerView(viewModel: MainViewModel, id: Int) {
    val list = viewModel.data.observeAsState()

    val currentItem = remember {
        Log.d("abba", "remember: $id")
        id
    }

    val currentIndex = list.value?.indexOf(currentItem) ?: id

    val pager2 = rememberPagerState()

    val coroutineScope = rememberCoroutineScope()
    coroutineScope.launch {
        pager2.scrollToPage(currentIndex)
    }

    Log.d("abba", "currentIndex: $currentIndex --- currentItem: $currentItem")

    VerticalPager(count = list.value?.size ?: 0, state = pager2) {
        val value = list.value?.get(currentPage)!!
        currentItem = value
        Log.d("abba", "PagerView: $currentItem")
        VideoItem(value = value)
    }
}

@Composable
fun VideoItem(value: Int) {
    Text(text = value.toString(), style = MaterialTheme.typography.h1)
}

@Composable
fun ColorScreen(items: List<MyData2>) {
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
fun ColorRow(data: MyData2) {
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


/*@Composable
fun HomeFragment(state: StateItem) {

    val stateScroll = rememberScrollState()

    val copyUrl = viewModel.copyUrl.asState()

    Header(isLoading = loading, viewModel.copyUrl)

}*/

fun getVideoItem(arg1: Int, arg2: Int) {

}


@Composable
fun ViewPreview() {
    Log.d("abba", "VideoItem: 1")
    Text(text = Random.nextInt(0, 100).toString(), style = MaterialTheme.typography.h1)
}


@Composable
fun Header(
    isLoading: Boolean,
    copyUrl: String,
    getTextFromClipboard: () -> String,
    catch: (String) -> Unit
) {
    val (url, changeUrl) = remember {
        mutableStateOf(copyUrl)
    }

    TextField(value = url, onValueChange = changeUrl)

    Button(onClick = {
        changeUrl(getTextFromClipboard())
    }) {
        Text(text = "paste")
    }

    Button(onClick = {
        catch(url)
    }) {
        Text(text = "Download")
    }
}


private fun SnapshotStateList<MyData2>.addToBottom() {
    this.add(MyData2())
}

private fun SnapshotStateList<MyData2>.addToTop() {
    this.add(0, MyData2())
}

private fun SnapshotStateList<MyData2>.addToMiddle() {
    this.add(this.size / 2, MyData2())
}

private fun getRandomColor(): Color {
    val red = Random.nextInt(0, 255)
    val green = Random.nextInt(0, 255)
    val blue = Random.nextInt(0, 255)
    return Color(red = red, green = green, blue = blue, alpha = 255)
}