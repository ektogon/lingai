package com.example.lingai.domain.models

data class UserModel(
    var userId: String? = null,
    val login: String,
    val password: String,
    val name: String
) {
}