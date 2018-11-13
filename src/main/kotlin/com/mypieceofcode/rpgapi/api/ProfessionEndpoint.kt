package com.mypieceofcode.rpgapi.api

import com.mypieceofcode.rpgapi.application.professions.ProfessionService
import com.mypieceofcode.rpgapi.application.professions.dto.NewProfessionDto
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/profession"])
class ProfessionEndpoint(
        val service: ProfessionService
) {

    @GetMapping
    fun getProfession() = service.getProfessions()

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    fun createProfession(@RequestBody dto: NewProfessionDto) {
        service.createProfession(dto)
    }

}