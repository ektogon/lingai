package com.example.lingai.ui.components

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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lingai.ui.theme.TextPrimary
import com.example.lingai.ui.theme.TextSecondary
import com.example.lingai.ui.model.LessonTopicUi
import com.example.lingai.ui.theme.*

@Composable
fun CategoryCard(
    modifier: Modifier = Modifier,
    data: LessonTopicUi,
    onClick: () -> Unit,
) {
    val topic = data.topic

    CardBlock(
        modifier = modifier
            .clickable { onClick() },
        backgroundColor = White
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
                    .rotate(data.rotation)
                    .background(
                        Brush.horizontalGradient(
                            listOf(
                                data.backgroundColor,
                                data.backgroundColor.copy(alpha = 0.85f)
                            )
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(text = topic.emoji, fontSize = 32.sp)
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = topic.title,
                style = MaterialTheme.typography.titleSmall,
                color = TextPrimary,
                fontWeight = FontWeight.SemiBold,
                fontSize = 13.sp
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "${topic.totalWords} слов",
                fontSize = 12.sp,
                color = TextSecondary
            )

            Spacer(modifier = Modifier.height(8.dp))

            ProgressBar(data.progress)
        }
    }
}

