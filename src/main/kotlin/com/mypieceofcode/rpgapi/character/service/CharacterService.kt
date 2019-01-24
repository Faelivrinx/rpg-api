package com.mypieceofcode.rpgapi.character.service

import com.mypieceofcode.rpgapi.application.user.AuthorizationService
import com.mypieceofcode.rpgapi.character.dto.armors.CharacterArmorDto
import com.mypieceofcode.rpgapi.character.repository.CharacterRepository
import com.mypieceofcode.rpgapi.character.dto.info.CharacterDto
import com.mypieceofcode.rpgapi.character.model.CharacterTraits
import com.mypieceofcode.rpgapi.character.service.factory.CharacterFactory
import com.mypieceofcode.rpgapi.domain.DbUserRepository
import com.mypieceofcode.rpgapi.exceptions.DomainException
import com.mypieceofcode.rpgapi.exceptions.ErrorCode
import com.mypieceofcode.rpgapi.exceptions.MissingEntityException
import com.mypieceofcode.rpgapi.exceptions.UnauthorizeException
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate

@Component
data class CharacterService (
        val repository: CharacterRepository,
        val factory: CharacterFactory,
        val authorizationService: RestTemplate,
        val userRepo: DbUserRepository
){
    @Value("\${auth.url}")
    private lateinit var authUrl: String


    fun getCharacterById(id: String) : CharacterDto {
        val character = repository.findById(id)

        if(character.isPresent){
            return CharacterDto.to(character.get())
        }
        throw MissingEntityException(ErrorCode.CHARACTER_NOT_FOUND)
    }

    fun updateTraits(id: String, traits: List<CharacterTraits>) {
        val optional = repository.findById(id)
        if(optional.isPresent){
            val character = optional.get()
            character.traits.clear()
            character.traits.addAll(traits)
            repository.save(character)
            return
        }
        throw MissingEntityException(ErrorCode.CHARACTER_NOT_FOUND)
    }

    fun getArmors(id: String) : List<CharacterArmorDto> {
        val optional = repository.findById(id)
        if(optional.isPresent){
            val character = optional.get()
            return factory.createArmors(character)
        }
        throw MissingEntityException(ErrorCode.CHARACTER_NOT_FOUND)
    }

    fun increaseTrait(id: String, dto: CharacterTraits) {
        val optional = repository.findById(id)
        if(optional.isPresent){
            val character = optional.get()
            if(character.xp >= 100){
                dto.currentValue = dto.currentValue + dto.progressStep
                val traits = character.traits.map { if (it.name == dto.name) dto else it }.toMutableList()
                character.traits.clear()
                character.traits.addAll(traits)
                character.xp = character.xp - 100
                repository.save(character)
                return
            } else {
                throw DomainException(ErrorCode.CHARACTER_NOT_ENOUGH_XP)
            }

        }
        throw MissingEntityException(ErrorCode.CHARACTER_NOT_FOUND)
    }

    fun getCharacter(token: String): CharacterDto {
        val headers = HttpHeaders()
        headers.add("Authorization", token)
        val httpEntity = HttpEntity("parameters", headers)
        try {
            val username = authorizationService.exchange("$authUrl/user", HttpMethod.GET, httpEntity, String::class.java)
            val user = userRepo.findByUsername(username.body!!) ?: throw MissingEntityException(ErrorCode.USER_NOT_FOUND)
            return CharacterDto.to(user.character)
        } catch (e: HttpClientErrorException){
            throw MissingEntityException(ErrorCode.CHARACTER_NOT_FOUND)
        }
        throw UnauthorizeException(ErrorCode.INVALID_AUTH_TOKEN)
    }
}