package com.mypieceofcode.rpgapi.application.user.response

data class AuthorizationResult(
        val accessToken: String,
        val tokenType: String,
        val expiresIn: Long
) {

}