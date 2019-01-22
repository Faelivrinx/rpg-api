package com.mypieceofcode.rpgapi.application.armors

import com.mypieceofcode.rpgapi.application.armors.dto.ArmorDto
import com.mypieceofcode.rpgapi.application.armors.dto.NewArmorDto
import com.mypieceofcode.rpgapi.application.armors.dto.UpdateArmorDto
import com.mypieceofcode.rpgapi.domain.enums.ArmorType
import com.mypieceofcode.rpgapi.domain.equipment.armors.ArmorRepository
import com.mypieceofcode.rpgapi.exceptions.EntityAlreadyExistsException
import com.mypieceofcode.rpgapi.exceptions.ErrorCode
import com.mypieceofcode.rpgapi.exceptions.MissingEntityException
import org.springframework.stereotype.Service

@Service
class ArmorService(
        val repository: ArmorRepository
) {

    fun getArmors(): List<ArmorDto> = repository.findAll().map { ArmorDto.fromArmor(it) }

    fun createArmor(dto: NewArmorDto) {
        val armor = repository.findByName(dto.name)
        when (armor != null && armor.type != ArmorType.createArmorType(dto.type)) {
            true -> {
                throw EntityAlreadyExistsException(ErrorCode.ARMOR_ALREADY_EXISTS)
            }
            else -> repository.create(NewArmorDto.toArmor(dto))
        }
    }

    fun getArmorById(id: String): ArmorDto {
        val item = repository.findById(id)
        return if (item.isPresent) {
            ArmorDto.fromArmor(item.get())
        } else {
            throw MissingEntityException(ErrorCode.ARMOR_NOT_FOUND)
        }
    }

    fun updateArmor(dto: UpdateArmorDto) {
        val armor = repository.findById(dto.id)
        if (armor.isPresent) {
            repository.create(UpdateArmorDto.toArmor(dto))
        } else {
            throw MissingEntityException(ErrorCode.ARMOR_NOT_FOUND)
        }
    }

    fun deleteArmorById(id: String) {
        when (repository.existsById(id)) {
            true -> {
                repository.deleteById(id)
            }
            else -> {
                throw MissingEntityException(ErrorCode.ARMOR_NOT_FOUND)
            }
        }
    }
}