package com.mypieceofcode.rpgapi.application.user.request

data class LoginRequest(
        val usernameOrEmail: String,
        val password: String
) {

}