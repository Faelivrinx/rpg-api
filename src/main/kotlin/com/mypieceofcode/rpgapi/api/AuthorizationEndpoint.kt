package com.mypieceofcode.rpgapi.api

import com.mypieceofcode.rpgapi.application.user.AuthorizationResult
import com.mypieceofcode.rpgapi.application.user.AuthorizationService
import com.mypieceofcode.rpgapi.application.user.LoginDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/auth"])
class AuthorizationEndpoint {

    @Autowired
    lateinit var authService: AuthorizationService

    @PostMapping(value = ["/login"])
    fun login(@RequestBody dto: LoginDto) : AuthorizationResult{
        return authService.loginUser(dto)
    }

}