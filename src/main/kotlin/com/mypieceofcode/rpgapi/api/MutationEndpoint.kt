package com.mypieceofcode.rpgapi.api

import com.mypieceofcode.rpgapi.application.mutations.MutationService
import com.mypieceofcode.rpgapi.application.mutations.dto.CreateMutationDto
import com.mypieceofcode.rpgapi.application.mutations.dto.MutationDto
import com.mypieceofcode.rpgapi.application.mutations.dto.UpdateMutationDto
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/mutations"])
class MutationEndpoint(
        val service: MutationService
) {

    @GetMapping
    fun getSpells(): List<MutationDto> = service.getMutations()

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    fun saveSpell(@RequestBody dto: CreateMutationDto) {
        service.createMutation(dto)
    }

    @GetMapping(value = ["/{id}"])
    @ResponseStatus(HttpStatus.OK)
    fun getSpell(@PathVariable id: String): MutationDto {
        return service.getMutationById(id)
    }

    @PutMapping
    fun updateSpell(@RequestBody dto: UpdateMutationDto) {
        service.updateMutation(dto)
    }

    @DeleteMapping(value = ["/{id}"])
    fun deleteSpell(@PathVariable id: String) {
        service.deleteMutation(id)
    }
}