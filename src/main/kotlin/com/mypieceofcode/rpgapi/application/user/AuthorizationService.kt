package com.mypieceofcode.rpgapi.application.user

import com.mypieceofcode.rpgapi.application.user.request.LoginRequest
import com.mypieceofcode.rpgapi.application.user.request.RegisterRequest
import com.mypieceofcode.rpgapi.application.user.response.AuthorizationResult
import com.mypieceofcode.rpgapi.application.user.response.RegisterResult
import com.mypieceofcode.rpgapi.character.DbCharacter
import com.mypieceofcode.rpgapi.character.repository.CharacterRepository
import com.mypieceofcode.rpgapi.domain.DbUserRepository
import com.mypieceofcode.rpgapi.domain.profession.ProfessionRepository
import com.mypieceofcode.rpgapi.exceptions.ErrorCode
import com.mypieceofcode.rpgapi.exceptions.ServiceUnavailableException
import com.mypieceofcode.rpgapi.exceptions.UnauthorizeException
import com.mypieceofcode.rpgapi.infrastructure.persistence.DbProfession
import com.mypieceofcode.rpgapi.infrastructure.persistence.DbUser
import com.mypieceofcode.rpgapi.infrastructure.professions.DbProfessionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.*
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate
import java.net.ConnectException

@Component
class AuthorizationService {

    @Autowired
    private lateinit var authorizationService: RestTemplate

    @Autowired
    private lateinit var characterRepository: CharacterRepository

    @Autowired
    private lateinit var repoProfession: DbProfessionRepository

    @Autowired
    private lateinit var repoUser: DbUserRepository


    @Value("\${auth.url}")
    private lateinit var authUrl: String


    fun loginUser(dto: LoginDto): AuthorizationResult {
        try {
            val response = authorizationService.postForEntity("$authUrl/login", prepareDto(dto), AuthorizationResult::class.java)
            if (response.body != null) {
                return response.body!!
            }
        } catch (e: HttpClientErrorException) {
            throw UnauthorizeException(ErrorCode.LOGIN_REQUEST_FAILED)
        } catch(e: ConnectException){
            throw ServiceUnavailableException(ErrorCode.AUTHORIZATION_SERVICE_UNAVAILABLE)
        }

        throw IllegalArgumentException("INVALID RESPONSE")
    }

    fun registerUser(dto: RegisterRequest) : RegisterResult {
        try {
            val response = authorizationService.postForEntity("$authUrl/register", dto, RegisterResult::class.java)
            if(response.body != null){
                val professions= repoProfession.findAll()
                if(!professions.isEmpty()){
                    val profession = professions.first()
                    val randomCharacter = DbCharacter.randomCharacter("random", "Człowiek", profession = profession)
                    val savedCharacter = characterRepository.save(randomCharacter)
                    repoUser.save(DbUser(savedCharacter, response.body!!.message))
                }
                return response.body!!
            }
        } catch (e: HttpClientErrorException) {
            when(e.statusCode) {
                HttpStatus.BAD_REQUEST -> throw UnauthorizeException(ErrorCode.REGISTER_BAD_CREDENTIALS)
                else -> throw UnauthorizeException(ErrorCode.REGISTER_REQUEST_FAILED)
            }
        } catch(e: ConnectException){
            throw ServiceUnavailableException(ErrorCode.AUTHORIZATION_SERVICE_UNAVAILABLE)
        }
        throw IllegalArgumentException("INVALID RESPONSE")
    }

    fun validToken(token: String) : Boolean {
        val headers = HttpHeaders()
        headers.add("Authorization", token)
        val httpEntity = HttpEntity("parameters", headers)
        try {
             val response = authorizationService.exchange("$authUrl/valid", HttpMethod.POST, httpEntity, ResponseEntity::class.java)
            return if(response.statusCode == HttpStatus.OK){
                true
            } else {
                println(response.statusCode)
                false
            }
        } catch (e: HttpClientErrorException){
            return false
        }
    }

    private fun prepareDto(dto: LoginDto) : LoginRequest {
        return LoginRequest(dto.login, dto.password)
    }
}