package com.mypieceofcode.rpgapi.api

import com.mypieceofcode.rpgapi.application.items.ItemService
import com.mypieceofcode.rpgapi.application.items.dto.NewItemDto
import com.mypieceofcode.rpgapi.application.items.dto.UpdateItemDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/items"])
class ItemEndpoint{

    @Autowired lateinit var service: ItemService

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAllAbilities() = service.getItems()

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    fun createAbility(@RequestBody dto: NewItemDto) = service.createItem(dto)

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    fun updateAbility(@RequestBody dto: UpdateItemDto) = service.updateItem(dto)

    @GetMapping(value = ["/{id}"])
    @ResponseStatus(HttpStatus.OK)
    fun getAbilityById(@PathVariable id: String) = service.getItemById(id)

    @DeleteMapping(value = ["/{id}"])
    @ResponseStatus(HttpStatus.OK)
    fun deleteAbilityById(@PathVariable id: String) = service.deleteItemById(id)

}
