package com.mypieceofcode.rpgapi.api

import com.mypieceofcode.rpgapi.character.dto.CharacterDto
import com.mypieceofcode.rpgapi.character.service.CharacterService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/character"])
class CharacterEndpoint(
        val service: CharacterService
) {

    @GetMapping(value = ["/{id}"])
    fun getCharacter(@PathVariable id: String) : CharacterDto{
        return service.getCharacterById(id)
    }
}