package com.mypieceofcode.rpgapi.api

import com.mypieceofcode.rpgapi.application.armors.ArmorService
import com.mypieceofcode.rpgapi.application.armors.dto.NewArmorDto
import com.mypieceofcode.rpgapi.application.armors.dto.UpdateArmorDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/armors"])
class ArmorEndpoint {

    @Autowired
    lateinit var service: ArmorService

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAllArmors() = service.getArmors()

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    fun createArmor(@RequestBody dto: NewArmorDto) = service.createArmor(dto)

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    fun updateArmor(@RequestBody dto: UpdateArmorDto) = service.updateArmor(dto)

    @GetMapping(value = ["/{id}"])
    @ResponseStatus(HttpStatus.OK)
    fun getArmorById(@PathVariable id: String) = service.getArmorById(id)

    @DeleteMapping(value = ["/{id}"])
    @ResponseStatus(HttpStatus.OK)
    fun deleteArmorById(@PathVariable id: String) = service.deleteArmorById(id)
}