package com.mypieceofcode.rpgapi.api

import com.mypieceofcode.rpgapi.application.spells.SpellService
import com.mypieceofcode.rpgapi.application.spells.dto.CreateSpellDto
import com.mypieceofcode.rpgapi.application.spells.dto.SpellDto
import com.mypieceofcode.rpgapi.application.spells.dto.UpdateSpellDto
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/spells"])
class SpellEndpoint(
    val service: SpellService
) {

    @GetMapping
    fun getSpells(): List<SpellDto> = service.getSpells()

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    fun saveSpell(@RequestBody dto: CreateSpellDto) {
        service.createSpell(dto)
    }

    @GetMapping(value = ["/{id}"])
    @ResponseStatus(HttpStatus.OK)
    fun getSpell(@PathVariable id: String): SpellDto {
        return service.getSpellById(id)
    }

    @PutMapping
    fun updateSpell(@RequestBody dto: UpdateSpellDto) {
        service.updateSpell(dto)
    }

    @DeleteMapping(value = ["/{id}"])
    fun deleteSpell(@PathVariable id: String){
        service.deleteSpell(id)
    }

}