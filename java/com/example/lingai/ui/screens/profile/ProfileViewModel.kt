package com.example.lingai.ui.screens.profile

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lingai.domain.models.NetworkResult
import com.example.lingai.domain.models.UserModel
import com.example.lingai.domain.repository.AuthRepository
import com.example.lingai.ui.theme.Blue600
import com.example.lingai.ui.theme.Blue700
import com.example.lingai.ui.theme.BluePrimary
import com.example.lingai.ui.theme.Gold
import com.example.lingai.ui.theme.GoldDark
import com.example.lingai.ui.theme.GreenLight
import com.example.lingai.ui.theme.GreenPrimary
import com.example.lingai.ui.theme.OrangeLight
import com.example.lingai.ui.theme.OrangePrimary
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class Stat(val emoji: String, val label: String, val value: String, val color: Color)
data class Achievement(
    val emoji: String,
    val title: String,
    val description: String,
    val background: Brush,
    val iconBackground: Brush
)

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    private var _userName = MutableStateFlow("Имя пользователя")
    val userName: StateFlow<String> = _userName
    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val _isLoading = MutableStateFlow(false) // Состояние для отслеживания загрузки
    val isLoading: StateFlow<Boolean> = _isLoading // Экспозиция состояния в UI

    private val _stats = MutableStateFlow(
        listOf(
            Stat("🔥", "Дней подряд", "15", Gold),
            Stat("💎", "Очков", "2,845", GreenPrimary),
            Stat("🎯", "Слов изучено", "342", BluePrimary),
            Stat("⏱️", "Минут сегодня", "45", OrangePrimary)
        )
    )
    val stats: StateFlow<List<Stat>> = _stats

    private val _achievements = MutableStateFlow(
        listOf(
            Achievement(
                "🏆",
                "Первая неделя",
                "7 дней подряд",
                Brush.horizontalGradient(listOf(OrangeLight, Color(0xFFFFD89B))),
                Brush.horizontalGradient(listOf(Gold, GoldDark))
            ), Achievement(
                "📚",
                "Знаток слов",
                "300 слов изучено",
                Brush.horizontalGradient(listOf(GreenLight, GreenLight)),
                Brush.horizontalGradient(listOf(GreenPrimary, GreenLight))
            ), Achievement(
                "⚡",
                "Быстрый старт",
                "Первая тема завершена",
                Brush.horizontalGradient(listOf(GreenLight, Color(0xFFD4EDE0))),
                Brush.horizontalGradient(listOf(Blue600, Blue700))
            )
        )
    )
    val achievements: StateFlow<List<Achievement>> = _achievements

    // Состояние для отображения диалога входа
    private val _isLoginDialogVisible = MutableStateFlow(false)
    val isLoginDialogVisible: StateFlow<Boolean> = _isLoginDialogVisible

    private val _isRegisterDialogVisible = MutableStateFlow(false)
    val isRegisterDialogVisible: StateFlow<Boolean> = _isRegisterDialogVisible

    var name = mutableStateOf("")
    var email = mutableStateOf("")
    var password = mutableStateOf("")

    // Логика для показа диалогов
    fun showLoginDialog() {
        _isLoginDialogVisible.value = true
        _isRegisterDialogVisible.value = false // Скрыть регистрацию, если показываем вход
    }

    fun showRegisterDialog() {
        _isRegisterDialogVisible.value = true
        _isLoginDialogVisible.value = false // Скрыть вход, если показываем регистрацию
    }

    // Метод для скрытия диалогов
    fun dismissDialogs() {
        _isLoginDialogVisible.value = false
        _isRegisterDialogVisible.value = false
    }


    fun loadCurrentUserName() {
        viewModelScope.launch {
            authRepository.getCurrentUserName().collect { result ->
                when (result) {
                    is NetworkResult.Success -> {
                        Log.d("ProfileViewModel", "User name loaded: ${result.data}")
                        _isLoggedIn.value = true
                        _userName.value =
                            result.data ?: "Unknown User"  // Устанавливаем имя пользователя
                    }

                    is NetworkResult.Error -> {
                        _error.value = "Error loading name"  // Обработка ошибки
                    }

                    is NetworkResult.Loading -> {
                        // Обработка состояния загрузки (например, можно показать индикатор загрузки)
                    }
                }
            }
        }
    }


    fun register() {
        viewModelScope.launch {
            val validationResult = validateCredentials(email.value, password.value)
            if (validationResult != null) {
                _error.value = validationResult
                return@launch
            }
            val user = UserModel(
                login = email.value,
                password = password.value,
                name = name.value
            )
            authRepository.firebaseSingUp(user).collect { result ->
                when (result) {
                    is NetworkResult.Success -> {
                        _isLoggedIn.value = true
                        loadCurrentUserName()
                        dismissDialogs()
                    }

                    is NetworkResult.Error -> _error.value = result.message
                    else -> {}
                }
            }
        }
    }

    fun login() {
        viewModelScope.launch {
            val validationResult = validateCredentials(email.value, password.value)
            if (validationResult != null) {
                _error.value = validationResult
                return@launch
            }
            _isLoading.value = true
            // Вызываем метод firebaseLogIn из репозитория
            authRepository.firebaseLogIn(email.value, password.value).collect { result ->
                _isLoading.value = false
                when (result) {
                    is NetworkResult.Success -> {
                        // Если вход успешен, обновляем состояние
                        _isLoggedIn.value = true
                        _userName.value = result.data?.name ?: "Unknown"
                        Log.d("login", "Success: User logged in successfully")
                        dismissDialogs() // Закрыть диалог входа
                    }

                    is NetworkResult.Error -> {
                        // Если произошла ошибка, выводим ее сообщение
                        _error.value = result.message
                        Log.d("login", "Error: ${result.message}")
                    }

                    is NetworkResult.Loading -> {
                        // Можно отобразить индикатор загрузки, если нужно
                        Log.d("login", "Logging in...")
                    }
                }
            }
        }
    }


    fun logOut() {
        viewModelScope.launch {
            authRepository.firebaseLogOut().collect { result ->
                if (result is NetworkResult.Success) _isLoggedIn.value = false
            }
        }
    }

    fun updateName(newName: String) {
        name.value = newName
    }

    // Метод для обновления email
    fun updateEmail(newEmail: String) {
        email.value = newEmail
    }

    // Метод для обновления пароля
    fun updatePassword(newPassword: String) {
        password.value = newPassword
    }

    // Валидация email и пароля
    private fun validateCredentials(email: String, password: String): String? {
        if (email.isBlank()) {
            return "Email не может быть пустым"
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return "Неверный формат email"
        }

        if (password.length < 8) {
            return "Пароль должен содержать минимум 8 символов"
        }

        return null
    }
}