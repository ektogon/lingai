package com.example.lingai.domain.repository

import com.example.lingai.domain.models.NetworkResult
import com.example.lingai.domain.models.UserModel
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun getCurrentUserName(): Flow<NetworkResult<String>>

    suspend fun firebaseSingUp(user: UserModel): Flow<NetworkResult<Boolean>>
    suspend fun firebaseLogIn(email: String,password: String): Flow<NetworkResult<UserModel>>
    suspend fun firebaseLogOut(): Flow<NetworkResult<Boolean>>
}