package com.example.lingai.data.firebase

import android.util.Log
import com.example.lingai.domain.models.UserModel
import com.example.lingai.domain.repository.AuthRepository
import com.example.lingai.utils.NetworkResult
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
    override suspend fun getCurrentUser(): Flow<NetworkResult<UserModel>> {
        return flow {
            val firebaseUser = firebaseAuth.currentUser
            if (firebaseUser != null) {
                val userName = firebaseUser.displayName ?: "Неизвестный пользователь"
                val userEmail = firebaseUser.email ?: "Неизвестный Email"
                val userModel = UserModel(
                    userId = firebaseUser.uid,
                    login = userEmail,
                    password = "",  // Пароль не хранится
                    name = userName
                )
                emit(NetworkResult.Success(userModel))
            } else {
                // Логика для гостевого пользователя (если нужно)
                val guestUser = UserModel(
                    userId = "__guest__",
                    login = "",
                    password = "",
                    name = ""
                )
                emit(NetworkResult.Success(guestUser))
            }
        }
    }

    override suspend fun getCurrentUserName(): Flow<NetworkResult<String>> {
        return flow {
            val userName = FirebaseAuth.getInstance().currentUser?.displayName ?: "Unknown User"
            Log.d(TAG, "Current user name: $userName")
            emit(NetworkResult.Success(userName))
        }
    }

    override suspend fun firebaseSingUp(user: UserModel): Flow<NetworkResult<Boolean>> {
        return flow {
            try {
                emit(NetworkResult.Loading())
                firebaseAuth.createUserWithEmailAndPassword(user.login, user.password).await()
                val firebaseUser = firebaseAuth.currentUser
                if(firebaseUser != null) {
                    val profileUpdates = UserProfileChangeRequest.Builder().setDisplayName(user.name).build()
                    firebaseUser.updateProfile(profileUpdates).await()
                    val userData = mapOf(
                        "name" to user.name,
                        "email" to user.login
                    )
                    firebaseFirestore.collection("users").document(firebaseUser.uid)
                        .set(userData)
                        .await()
                    emit(NetworkResult.Success(true))
                }
                else {
                    emit(NetworkResult.Error("Registration failed"))
                }
            }catch (e: Exception){
                emit(NetworkResult.Error(e.message ?: "Registration failed"))
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
                    val userEmail =
                        userDoc.getString("email") ?: email // Если email пустой, берем из Firebase

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
