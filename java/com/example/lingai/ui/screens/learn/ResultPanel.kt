package com.example.lingai.ui.screens.learn

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lingai.ui.theme.*

@Composable
fun ResultPanel(
    isCorrect: Boolean,
    onNext: () -> Unit
) {
    val bgColor = if (isCorrect) Correct else Wrong

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(146.dp)
            .background(bgColor),
        Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 33.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    if (isCorrect) Icons.Default.Check else Icons.Default.Close,
                    contentDescription = null,
                    tint = White,
                    modifier = Modifier.size(32.dp)
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    if (isCorrect) "Правильно!" else "Неправильно!",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = White
                )
            }
            Spacer(Modifier.height(16.dp))
            Button(
                onClick = onNext,
                modifier = Modifier.fillMaxWidth().height(58.dp),
                colors = ButtonDefaults.buttonColors(containerColor = White),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("ПРОДОЛЖИТЬ", color = bgColor, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}
