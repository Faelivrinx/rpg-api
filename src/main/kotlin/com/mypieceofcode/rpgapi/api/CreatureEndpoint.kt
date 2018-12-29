package com.mypieceofcode.rpgapi.api

import com.mypieceofcode.rpgapi.application.creatures.CreatureService
import com.mypieceofcode.rpgapi.application.creatures.dto.CreateCreatureDto
import com.mypieceofcode.rpgapi.application.creatures.dto.UpdateCreatureDto
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/creatures"])
class CreatureEndpoint(
        val service: CreatureService
) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getCreatures() = service.getCreatures()

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    fun createCreature(@RequestBody dto: CreateCreatureDto) = service.createNewCreature(dto)

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    fun updateCreature(@RequestBody dto: UpdateCreatureDto) = service.updateCreature(dto)

    @DeleteMapping(value = ["/{id}"])
    @ResponseStatus(HttpStatus.OK)
    fun deleteCreature(@PathVariable id: String) = service.deleteCreature(id)

    @GetMapping(value = ["/{id}"])
    @ResponseStatus(HttpStatus.OK)
    fun getCreatureById(@PathVariable id: String) = service.getCreatureById(id)

}