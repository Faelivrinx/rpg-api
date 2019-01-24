package com.mypieceofcode.rpgapi.api

import com.mypieceofcode.rpgapi.character.dto.armors.CharacterArmorDto
import com.mypieceofcode.rpgapi.character.dto.info.CharacterDto
import com.mypieceofcode.rpgapi.character.model.CharacterTraits
import com.mypieceofcode.rpgapi.character.service.CharacterService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/character"])
class CharacterEndpoint(
        val service: CharacterService
) {

    @PutMapping(value = ["/{id}/traits"])
    @ResponseStatus(HttpStatus.OK)
    fun updateTraits(@RequestBody dto: List<CharacterTraits>, @PathVariable id: String) {
        service.updateTraits(id, dto)
    }

    @GetMapping
    fun getCharacter(@RequestHeader(value = "Authorization") token: String): CharacterDto {
        return service.getCharacter(token)
    }

    @GetMapping(value = ["/{id}/armors"])
    fun getArmors(@PathVariable id: String): List<CharacterArmorDto> {
        return service.getArmors(id)
    }

    @PutMapping(value = ["/{id}/incrementTrait"])
    @ResponseStatus(HttpStatus.OK)
    fun updateTrait(@PathVariable id: String, @RequestBody dto: CharacterTraits){
        service.increaseTrait(id, dto)
    }


}