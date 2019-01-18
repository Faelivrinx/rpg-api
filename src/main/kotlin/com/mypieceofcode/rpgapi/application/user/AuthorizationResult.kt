package com.mypieceofcode.rpgapi.application.user

data class AuthorizationResult(
        val accessToken: String,
        val tokenType: String
) {

}