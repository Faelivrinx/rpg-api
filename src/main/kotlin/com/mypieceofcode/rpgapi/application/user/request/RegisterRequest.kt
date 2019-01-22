package com.mypieceofcode.rpgapi.application.user.request

data class RegisterRequest(
        val username: String,
        val email: String,
        val password: String
) {
}