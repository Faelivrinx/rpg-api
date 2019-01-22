package com.mypieceofcode.rpgapi.character.service

import com.mypieceofcode.rpgapi.character.CharacterRepository
import com.mypieceofcode.rpgapi.character.dto.CharacterDto
import com.mypieceofcode.rpgapi.exceptions.ErrorCode
import com.mypieceofcode.rpgapi.exceptions.MissingEntityException
import org.springframework.stereotype.Component

@Component
data class CharacterService (
        val repository: CharacterRepository
){

    fun getCharacterById(id: String) : CharacterDto {
        val character = repository.findById(id)

        if(character.isPresent){
            return CharacterDto.to(character.get())
        }
        throw MissingEntityException(ErrorCode.CHARACTER_NOT_FOUND)
    }
}