package com.example.lingai.domain.repository

import com.example.lingai.domain.models.UserModel
import com.example.lingai.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun getCurrentUser(): Flow<NetworkResult<UserModel>>
    suspend fun getCurrentUserName(): Flow<NetworkResult<String>>

    suspend fun firebaseSingUp(user: UserModel): Flow<NetworkResult<Boolean>>
    suspend fun firebaseLogIn(email: String,password: String): Flow<NetworkResult<UserModel>>
    suspend fun firebaseLogOut(): Flow<NetworkResult<Boolean>>
}