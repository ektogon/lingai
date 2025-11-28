package com.example.lingai.ui.screens.profile

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.lingai.R
import com.example.lingai.ui.components.ContentColumn
import com.example.lingai.ui.components.ThemeSwitcher
import com.example.lingai.ui.screens.dialogs.LoginDialog
import com.example.lingai.ui.screens.dialogs.RegisterDialog
import com.example.lingai.ui.theme.LingaiTheme
import com.example.lingai.ui.theme.TextSecondary
import com.example.lingai.ui.theme.ThemeGradients

// 🌿 Главный экран профиля
@Composable
fun ProfilePage(
    darkTheme: Boolean,
    onThemeUpdate: () -> Unit,
    viewModel: ProfileViewModel = hiltViewModel(),

    ) {
    val context = LocalContext.current
    val username = viewModel.userName.collectAsState().value
    val isLoggedIn by viewModel.isLoggedIn.collectAsState()
    val isLoginDialogVisible by viewModel.isLoginDialogVisible.collectAsState()
    val isRegisterDialogVisible by viewModel.isRegisterDialogVisible.collectAsState()
    val stats by viewModel.stats.collectAsState()
    val achievements by viewModel.achievements.collectAsState()
    val error by viewModel.error.collectAsState()
    val loading by viewModel.isLoading.collectAsState()
    // Если ошибка не null, показываем Toast
    LaunchedEffect(Unit) {
        viewModel.loadCurrentUserName()
    }
    LaunchedEffect(error) {
        error?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }
    ContentColumn(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Профиль",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onBackground,
            )

            ThemeSwitcher(
                darkTheme = darkTheme, size = 30.dp, padding = 5.dp, onClick = onThemeUpdate
            )
        }
        ProfileCard {
            if (isLoggedIn) UserInfo(
                username,
                { viewModel.logOut() }) else Welcome(onClick = { action ->
                if (action == "auth") {
                    viewModel.showLoginDialog()  // Показать диалог для входа
                } else {
                    viewModel.showRegisterDialog() // Показать диалог для регистрации
                }
            })
            Spacer(Modifier.height(20.dp))
            StatsGrid(stats)
        }
        Spacer(Modifier.height(20.dp))

        ProfileCard(title = "Недавние достижения") {
            achievements.forEachIndexed { i, ach ->
                AchievementCard(ach)
                if (i != achievements.lastIndex) Spacer(Modifier.height(10.dp))
            }
        }
        Spacer(Modifier.height(20.dp))
    }

    Spacer(Modifier.height(20.dp))
    if (isLoginDialogVisible) {
        LoginDialog(
            onDismiss = { viewModel.dismissDialogs() },
            onSwitchToRegister = { viewModel.showRegisterDialog() },
        )
    }
    if (isRegisterDialogVisible) {
        RegisterDialog(
            onDismiss = { viewModel.dismissDialogs() },
            onSwitchToLogin = { viewModel.showLoginDialog() },
        )
    }
    if (loading) {
        Box(
            modifier = Modifier
                .fillMaxSize()  // Заполняем весь экран
                .background(Color.Black.copy(alpha = 0.5f))
                .clickable(enabled = false, onClick = {})
                .zIndex(1000f)
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(60.dp)
            )
        }
    }
}


// =================== 🔽 Компоненты ===================
@Composable
fun Welcome(onClick: (String) -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Добро пожаловать!",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Войдите или зарегистрируйтесь, чтобы сохранять свой прогресс и достижения",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Buttons
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Button(
                onClick = { onClick("auth") },
                modifier = Modifier
                    .weight(1f)
                    .height(44.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text("Войти", fontWeight = FontWeight.SemiBold)
            }

            OutlinedButton(
                onClick = { onClick("reg") },
                modifier = Modifier
                    .weight(1f)
                    .height(44.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = MaterialTheme.colorScheme.primary
                ),
                border = ButtonDefaults.outlinedButtonBorder.copy(
                    width = 2.dp, brush = ThemeGradients.current.primaryGradient
                )
            ) {
                Text("Регистрация", fontWeight = FontWeight.SemiBold)
            }
        }
    }
}

@Composable
fun UserInfo(username: String, onLogout: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Avatar("ИП")
        Spacer(Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = username,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSecondary
            )
            Text(
                "Изучаю английский",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSecondary
            )
        }
        Button(
            onClick = onLogout,
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_log_out),
                contentDescription = "exit",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
fun Avatar(initials: String) {
    Box(
        modifier = Modifier
            .size(64.dp)
            .clip(CircleShape)
            .background(ThemeGradients.current.primaryGradient), contentAlignment = Alignment.Center
    ) {
        Text(initials, color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun StatsGrid(stats: List<Stat>) {
    Row(
        modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        stats.chunked(2).forEach { columnStats ->
            Column(
                modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                columnStats.forEach { StatCard(it) }
            }
        }
    }

}

@Composable
fun StatCard(stat: Stat) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(stat.emoji, fontSize = 22.sp, modifier = Modifier.padding(bottom = 6.dp))
            Text(stat.value, fontSize = 18.sp, color = stat.color, fontWeight = FontWeight.Bold)
            Text(stat.label, fontSize = 12.sp, color = TextSecondary, textAlign = TextAlign.Center)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePagePreview() {
    var darkTheme by remember { mutableStateOf(false) }

    // Включаем MaterialTheme с темой
    LingaiTheme(darkTheme = darkTheme) {
        ProfilePage(
            darkTheme = darkTheme, onThemeUpdate = { darkTheme = !darkTheme })
    }
}
