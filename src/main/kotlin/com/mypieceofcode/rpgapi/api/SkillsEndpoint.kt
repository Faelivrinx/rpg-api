package com.mypieceofcode.rpgapi.api

import com.mypieceofcode.rpgapi.application.skills.SkillService
import com.mypieceofcode.rpgapi.application.skills.dto.NewSkillDto
import com.mypieceofcode.rpgapi.application.skills.dto.SkillDto
import com.mypieceofcode.rpgapi.application.skills.dto.UpdateSkillDto
import com.mypieceofcode.rpgapi.domain.skills.SkillRepository
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/skills"])
class SkillsEndpoint(
        val service: SkillService
) {

    @GetMapping
    fun getSkills(): List<SkillDto> = service.getSkills()

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    fun saveSkill(@RequestBody dto: NewSkillDto) {
        service.createSkill(dto)
    }

    @GetMapping(value = ["/{id}"])
    @ResponseStatus(HttpStatus.OK)
    fun getSkill(@PathVariable id: String): SkillDto {
        return service.getSkillById(id)
    }

    @PutMapping
    fun updateSkill(@RequestBody dto: UpdateSkillDto) {
        service.updateSkill(dto)
    }

    @DeleteMapping(value = ["/{id}"])
    fun deleteSkill(@PathVariable id: String){
        service.deleteSkill(id)
    }

}