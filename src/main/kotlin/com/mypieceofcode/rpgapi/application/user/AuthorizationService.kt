package com.mypieceofcode.rpgapi.application.user

import com.mypieceofcode.rpgapi.application.user.auth.LoginRequest
import com.mypieceofcode.rpgapi.exceptions.BadRequestException
import com.mypieceofcode.rpgapi.exceptions.ErrorCode
import com.mypieceofcode.rpgapi.exceptions.UnauthorizeException
import com.sun.org.apache.xpath.internal.operations.Bool
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.*
import org.springframework.stereotype.Component
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.exchange

@Component
class AuthorizationService {

    @Autowired
    lateinit var authorizationService: RestTemplate

    @Value("\${auth.key}")
    lateinit var authKey: String

    @Value("\${auth.url}")
    lateinit var authUrl: String


    fun loginUser(dto: LoginDto): AuthorizationResult {
        try {
            val response = authorizationService.postForEntity("$authUrl/login", prepareDto(dto), AuthorizationResult::class.java)
            if (response.body != null) {
                return response.body!!
            }
        } catch (e: HttpClientErrorException) {
            throw IllegalArgumentException("BAD_REQUEST")
        }

        throw IllegalArgumentException("INVALID RESPONSE")
    }

    fun validToken(token: String) : Boolean {
        val headers = HttpHeaders()
        headers.add("Authorization", token)
        val httpEntity = HttpEntity("parameters", headers)
        try {
            val response = authorizationService.exchange("$authUrl/valid", HttpMethod.POST, httpEntity, ResponseEntity::class.java)
            if(response.statusCode == HttpStatus.OK){
                return true
            } else {
                println(response.statusCode)
                   return false
            }
        } catch (e: HttpClientErrorException){
            if(e.message!!.contains("401")){
                throw UnauthorizeException(ErrorCode.INVALID_AUTH_TOKEN)
            } else if(e.message!!.contains("400")){
                throw BadRequestException(ErrorCode.EMPTY_AUTH_HEADER)
            }
            return false
        }
    }

    private fun prepareDto(dto: LoginDto) : LoginRequest {
        return LoginRequest(dto.login, dto.password)
    }
}