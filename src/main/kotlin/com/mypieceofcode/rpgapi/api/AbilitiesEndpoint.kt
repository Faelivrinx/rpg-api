package com.mypieceofcode.rpgapi.api

import com.mypieceofcode.rpgapi.application.abilities.AbilityService
import com.mypieceofcode.rpgapi.application.abilities.dto.NewAbilityDto
import com.mypieceofcode.rpgapi.application.abilities.dto.UpdateAbilityDto
import com.mypieceofcode.rpgapi.domain.abilities.AbilitiesRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus.OK
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/abilities"])
class AbilitiesEndpoint{

    @Autowired lateinit var service: AbilityService

    @GetMapping
    @ResponseStatus(OK)
    fun getAllAbilities() = service.getAbilities()

    @PostMapping
    @ResponseStatus(OK)
    fun createAbility(@RequestBody dto: NewAbilityDto) = service.createAbility(dto)

    @PutMapping
    @ResponseStatus(OK)
    fun updateAbility(@RequestBody dto: UpdateAbilityDto) = service.updateAbility(dto)

    @GetMapping(value = ["/{id}"])
    @ResponseStatus(OK)
    fun getAbilityById(@PathVariable id: String) = service.getAbilityById(id)

    @DeleteMapping(value = ["/{id}"])
    @ResponseStatus(OK)
    fun deleteAbilityById(@PathVariable id: String) = service.deleteAbility(id)
}