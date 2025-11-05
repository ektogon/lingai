package com.example.lingai.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.lingai.ui.theme.GreenLight
import com.example.lingai.ui.theme.GreenPrimary

@Composable
fun CardBlock(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.White,
    gradient: Brush? = null,
    innerPadding: Int = 20,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = GreenPrimary),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .let {
                    if (gradient != null)
                        it.background(brush = gradient)
                    else
                        it.background(color = backgroundColor)
                }
                .padding(innerPadding.dp)
        ) {
            content()
        }
    }
}