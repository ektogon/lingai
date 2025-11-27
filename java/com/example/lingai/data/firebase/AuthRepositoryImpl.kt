package com.example.lingai.data.firebase

import android.util.Log
import com.example.lingai.domain.models.NetworkResult
import com.example.lingai.domain.models.UserModel
import com.example.lingai.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore
) : AuthRepository {
    val TAG = "AuthRepositoryImpl"
    override suspend fun getCurrentUserName(): Flow<NetworkResult<String>> {
        return flow {
            val userName = FirebaseAuth.getInstance().currentUser?.displayName ?: "Unknown User"
            Log.d(TAG, "Current user name: $userName")
            emit(NetworkResult.Success(userName))
        }
    }

    override suspend fun firebaseSingUp(user: UserModel): Flow<NetworkResult<Boolean>> {
        return flow {
            var isSuccess = false

            emit(NetworkResult.Loading())
            try {
                firebaseAuth.createUserWithEmailAndPassword(user.login, user.password)
                    .addOnCompleteListener { task ->
                        isSuccess = if (task.isSuccessful) {
                            Log.d(TAG, "createUserWithEmailAndPassword:: success")
                            val firebaseUser = firebaseAuth.currentUser
                            if (firebaseUser != null) {
                                user.userId = firebaseUser.uid
                                val profileUpdates = UserProfileChangeRequest.Builder()
                                    .setDisplayName(user.name)  // Устанавливаем имя пользователя
                                    .build()

                                firebaseUser.updateProfile(profileUpdates)
                                    .addOnCompleteListener { profileTask ->
                                        if (profileTask.isSuccessful) {
                                            // Профиль обновлен успешно, можно продолжить
                                            Log.d(
                                                "Register",
                                                "User profile updated with name: ${firebaseUser.displayName}"
                                            )
                                        }
                                    }
                            }
                            firebaseFirestore.collection("users").document(firebaseUser?.uid ?: "")
                                .set(user)
                            true
                        } else {
                            Log.d(TAG, "createUserWithEmailAndPassword:: error", task.exception)
                            false
                        }
                    }.await()
                if (isSuccess) emit(NetworkResult.Success(true))
                else emit(NetworkResult.Error("Registration failed1"))
            } catch (e: Exception) {
                emit(
                    NetworkResult.Error(e.message ?: "Registration failed2")
                )
            }
        }

    }

    override suspend fun firebaseLogIn(
        email: String,
        password: String
    ): Flow<NetworkResult<UserModel>> {
        return flow {
            emit(NetworkResult.Loading())
            try {
                val authResult = firebaseAuth.signInWithEmailAndPassword(email, password).await()

                // Если вход в систему успешен
                if (authResult.user != null) {
                    Log.d(TAG, "signInWithEmailAndPassword:: success")

                    // Получаем данные пользователя из Firestore
                    val userDoc = firebaseFirestore.collection("users")
                        .document(authResult.user!!.uid)
                        .get()
                        .await()

                    // Получаем имя пользователя и email из Firestore
                    val userName = userDoc.getString("name") ?: "Неизвестный пользователь"
                    val userEmail = userDoc.getString("email") ?: email // Если email пустой, берем из Firebase

                    // Создаем объект UserModel
                    val userModel = UserModel(
                        userId = authResult.user!!.uid,
                        login = userEmail,
                        password = password, // Пароль не извлекаем из Firestore, так как он был использован при авторизации
                        name = userName
                    )

                    emit(NetworkResult.Success(userModel))
                } else {
                    emit(NetworkResult.Error("Ошибка авторизации"))
                }
            } catch (e: Exception) {
                Log.e(TAG, "Ошибка при авторизации", e)
                emit(NetworkResult.Error("Произошла ошибка при авторизации"))
            }
        }
    }


    override suspend fun firebaseLogOut(): Flow<NetworkResult<Boolean>> {
        return flow {
            try {
                firebaseAuth.signOut()
                emit(NetworkResult.Success(true))
            } catch (e: Exception) {
                emit(NetworkResult.Error("Logout failed"))
            }
        }
    }
}
