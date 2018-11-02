package com.mypieceofcode.rpgapi.application.items

import com.mypieceofcode.rpgapi.application.items.dto.ItemDto
import com.mypieceofcode.rpgapi.application.items.dto.NewItemDto
import com.mypieceofcode.rpgapi.application.items.dto.UpdateItemDto
import com.mypieceofcode.rpgapi.domain.equipment.items.ItemRepository
import com.mypieceofcode.rpgapi.exceptions.EntityAlreadyExistsException
import com.mypieceofcode.rpgapi.exceptions.ErrorCode
import com.mypieceofcode.rpgapi.exceptions.MissingEntityException
import org.springframework.stereotype.Service

@Service
class ItemService(
        val repository: ItemRepository
) {

    fun getItems(): List<ItemDto> = repository.findAll().map { ItemDto.fromItem(it) }

    fun createItem(dto: NewItemDto) {
        when (repository.existsByName(dto.name)) {
            true -> {
                throw EntityAlreadyExistsException(ErrorCode.ITEM_ALREADY_EXISTS)
            }
            else -> repository.create(NewItemDto.toItem(dto))
        }
    }

    fun getItemById(id: String): ItemDto {
        val item = repository.findById(id)
        return if (item.isPresent) {
            ItemDto.fromItem(item.get())
        } else {
            throw MissingEntityException(ErrorCode.ITEM_NOT_FOUND)
        }
    }

    fun updateItem(dto: UpdateItemDto) {
        val skill = repository.findById(dto.id)
        if (skill.isPresent) {
                repository.update(UpdateItemDto.toItem(dto))
        } else {
            throw MissingEntityException(ErrorCode.ITEM_NOT_FOUND)
        }
    }

    fun deleteItemById(id: String) {
        when (repository.existsById(id)) {
            true -> {
                repository.deleteById(id)
            }
            else -> {
                throw MissingEntityException(ErrorCode.ITEM_NOT_FOUND)
            }
        }
    }
}