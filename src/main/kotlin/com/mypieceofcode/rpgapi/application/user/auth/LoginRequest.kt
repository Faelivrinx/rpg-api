package com.mypieceofcode.rpgapi.application.user.auth

data class LoginRequest(
        val usernameOrEmail: String,
        val password: String
) {

}