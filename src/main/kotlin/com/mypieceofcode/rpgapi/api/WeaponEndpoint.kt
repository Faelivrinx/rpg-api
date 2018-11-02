package com.mypieceofcode.rpgapi.api

import com.mypieceofcode.rpgapi.application.weapons.WeaponService
import com.mypieceofcode.rpgapi.application.weapons.dto.NewWeaponDto
import com.mypieceofcode.rpgapi.application.weapons.dto.UpdateWeaponDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/weapons"])
class WeaponEndpoint {

    @Autowired
    lateinit var service: WeaponService

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAllArmors() = service.getWeapons()

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    fun createArmor(@RequestBody dto: NewWeaponDto) = service.createWeapon(dto)

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    fun updateArmor(@RequestBody dto: UpdateWeaponDto) = service.updateWeapon(dto)

    @GetMapping(value = ["/{id}"])
    @ResponseStatus(HttpStatus.OK)
    fun getArmorById(@PathVariable id: String) = service.getWeaponById(id)

    @DeleteMapping(value = ["/{id}"])
    @ResponseStatus(HttpStatus.OK)
    fun deleteArmorById(@PathVariable id: String) = service.deleteWeapon(id)
}