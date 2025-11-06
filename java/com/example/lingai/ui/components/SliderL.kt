package com.example.lingai.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lingai.ui.theme.*

@Composable
fun LinguaSlider(
    label: String,
    value: Float,
    valueRange: ClosedFloatingPointRange<Float>,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
    steps: Int = 0,
    valueSuffix: String = "",
    showRangeLabels: Boolean = true,
    accentColor: Color = Color.White, // 🎨 новый параметр цвета
    textColor: Color = Color.White    // позволяет менять цвет текста
) {
    Column(modifier = modifier) {
        // Заголовок и текущее значение
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        ) {
            Text(
                text = label,
                fontSize = 14.sp,
                color = textColor
            )
            Text(
                text = "${value.toInt()}$valueSuffix",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = textColor
            )
        }

        // Сам слайдер
        Slider(
            value = value,
            onValueChange = onValueChange,
            valueRange = valueRange,
            steps = steps,
            colors = SliderDefaults.colors(
                thumbColor = accentColor,
                activeTrackColor = accentColor,
                inactiveTrackColor = accentColor.copy(alpha = 0.3f)
            ),
            modifier = Modifier.fillMaxWidth()
        )

        // Нижние подписи диапазона
        if (showRangeLabels) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = valueRange.start.toInt().toString(),
                    fontSize = 12.sp,
                    color = textColor.copy(alpha = 0.8f)
                )
                Text(
                    text = valueRange.endInclusive.toInt().toString(),
                    fontSize = 12.sp,
                    color = textColor.copy(alpha = 0.8f)
                )
            }
        }
    }
}