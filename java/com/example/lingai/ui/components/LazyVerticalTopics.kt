package com.example.lingai.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lingai.ui.screens.LessonTopic

@Composable
fun LazyVerticalTopics(topics: List<LessonTopic>) {
    LazyVerticalGrid(
    columns = GridCells.Fixed(2),
    verticalArrangement = Arrangement.spacedBy(12.dp),
    horizontalArrangement = Arrangement.spacedBy(12.dp),
    modifier = Modifier.fillMaxSize(),
    contentPadding = PaddingValues(vertical = 15.dp)

    ) {
        items(topics) { topic ->
            CategoryCard(topic = topic)
        }
    }
}
@Preview
@Composable
fun LazyVerticalTopicsPrew(){
    val topics = listOf(    LessonTopic("family", "Семья и друзья", 50, 40, emoji = "👨‍👩‍👧"),
        LessonTopic("hobbies", "Хобби и увлечения", 60, 15, emoji = "🎨"))

    LazyVerticalTopics(topics)
}