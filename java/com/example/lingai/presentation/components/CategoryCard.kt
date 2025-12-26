package com.example.lingai.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lingai.presentation.theme.ThemeGradients
import com.example.lingai.presentation.ui_models.LessonTopicUi

@Composable
fun CategoryCard(
    modifier: Modifier = Modifier,
    topicUi: LessonTopicUi,
    onClick: () -> Unit,
) {
    CardBlock(
        modifier = modifier
            .clickable { onClick() },
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Box(
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .rotate(topicUi.rotation)
                    .background(
                        ThemeGradients.current.primaryGradient
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(text = topicUi.topic.emoji, fontSize = 32.sp)
            }

            Spacer(modifier = Modifier.height(12.dp))

            Box(modifier = Modifier.height(38.dp), contentAlignment = Alignment.Center) {
                Text(
                    text = topicUi.topic.translation,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSecondary,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "${topicUi.topic.totalWords} слов",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSecondary
            )



            Spacer(modifier = Modifier.height(8.dp))

            ProgressBar(topicUi.progress)
        }
    }
}

