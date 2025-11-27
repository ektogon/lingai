package com.example.lingai.ui.screens.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lingai.domain.models.LessonTopicModel
import com.example.lingai.domain.models.Word
import com.example.lingai.domain.models.WordStatus
import com.example.lingai.ui.components.ProgressBar

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
@Composable
fun TopicDetailsDialog(
    topic: LessonTopicModel,
    onDismiss: () -> Unit,
    onStartLearning: (Int) -> Unit
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    ModalBottomSheet(
        modifier = Modifier
            .windowInsetsPadding(WindowInsets.statusBars),
        onDismissRequest = { onDismiss() },
        sheetState = sheetState,
        dragHandle = {
            Box(
                Modifier
                    .padding(top = 12.dp)
                    .width(40.dp)
                    .height(5.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray)
            )
        },
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Column(
                Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(topic.emoji, fontSize = 40.sp)
                Text(
                    topic.title,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSecondary,
                    fontWeight = FontWeight.Bold
                )

                val progress = topic.completedWords.toFloat() / topic.totalWords.toFloat()
                ProgressBar(value = progress)

                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = "${topic.completedWords}/${topic.totalWords}",
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                }

                Divider(thickness = 1.dp, color = Color(0xFFE0E0E0))

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    items(topic.words) { word ->
                        WordRow(word)
                    }
                }
                Button(
                    onClick = { onStartLearning(topic.id) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text("Учить слова")
                }
            }
        }
    }
}

@Composable
fun WordRow(word: Word) {
    val color = when (word.status) {
        WordStatus.LEARNED -> Color(0xFF4CAF50)
        WordStatus.IN_PROGRESS -> Color(0xFFFFC107)
        WordStatus.NEW -> Color(0xFF9E9E9E)
    }

    Row(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            Modifier
                .size(10.dp)
                .background(color, CircleShape)
        )
        Spacer(Modifier.width(8.dp))
        Text("${word.original} [${word.transcription}]– ${word.translation}", fontSize = 14.sp)
    }
}

