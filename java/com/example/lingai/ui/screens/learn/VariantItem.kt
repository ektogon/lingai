package com.example.lingai.ui.screens.learn

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lingai.ui.theme.Correct
import com.example.lingai.ui.theme.CorrectLight
import com.example.lingai.ui.theme.RedPrimary
import com.example.lingai.ui.theme.WrongLight

@Composable
fun VariantItem(
    number: Int,
    text: String,
    isSelected: Boolean,
    isCorrect: Boolean,
    showResult: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = when {
        !showResult -> MaterialTheme.colorScheme.surface
        isCorrect -> CorrectLight.copy(alpha = 0.3f)
        isSelected && !isCorrect -> WrongLight.copy(alpha = 0.3f)
        else -> MaterialTheme.colorScheme.surface
    }

    val borderColor = when {
        !showResult -> MaterialTheme.colorScheme.secondaryContainer
        isCorrect -> Correct.copy(alpha = 0.5f)
        isSelected && !isCorrect -> RedPrimary
        else -> MaterialTheme.colorScheme.secondaryContainer
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(backgroundColor)
            .border(2.dp, borderColor, RoundedCornerShape(16.dp))
            .clickable(enabled = !showResult, onClick = onClick)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            Modifier
                .size(40.dp)
                .background(MaterialTheme.colorScheme.background, RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(number.toString(), style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onSecondary)
        }

        Spacer(Modifier.width(8.dp))
        Text(
            text = text,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSecondary
        )
    }
}
