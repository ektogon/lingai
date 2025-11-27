package com.example.lingai.ui.screens.learn.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lingai.R
import com.example.lingai.ui.theme.Correct
import com.example.lingai.ui.theme.White
import com.example.lingai.ui.theme.RedPrimary

@Composable
fun ResultPanel(
    modifier: Modifier = Modifier,
    isCorrect: Boolean,
    onNext: () -> Unit
) {
    val bgColor = if (isCorrect) Correct else RedPrimary

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(146.dp)
            .background(bgColor),
        Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(
                        id = if (isCorrect) R.drawable.ic_correct else R.drawable.ic_wrong
                    ),
                    tint = Color.Unspecified,
                    contentDescription = null,
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
                modifier = Modifier
                    .fillMaxWidth()
                    .height(58.dp),
                colors = ButtonDefaults.buttonColors(containerColor = White),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("ПРОДОЛЖИТЬ", color = bgColor, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}
