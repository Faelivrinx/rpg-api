package com.mypieceofcode.rpgapi.domain.equipment.armors

import com.mypieceofcode.rpgapi.domain.DomainObject
import com.mypieceofcode.rpgapi.domain.custom.Price
import com.mypieceofcode.rpgapi.domain.enums.ArmorType
import com.mypieceofcode.rpgapi.domain.enums.Availability
import com.mypieceofcode.rpgapi.domain.enums.ProtectionArea
import com.mypieceofcode.rpgapi.exceptions.ErrorCode
import com.mypieceofcode.rpgapi.exceptions.InvalidEntityException
import org.apache.commons.lang3.StringUtils.isBlank

data class Armor (
        val name: String,
        val type: ArmorType,
        val protectionArea: List<ProtectionArea>,
        val pz: Int,
        val price: Price,
        val weight: Float,
        val availability: Availability,
        val description: String,
        val url: String,
        val id: String? = null

) : DomainObject() {
    init {
        validate()
    }

    override fun validate() {
        validIfEmpty()
        validPriceAndPz(this.price, this.pz)
        validProtectionArea(this.protectionArea)
    }

    private fun validProtectionArea(protectionArea: List<ProtectionArea>) {
        protectionArea.forEach {
            if (it == ProtectionArea.INVALID){
                throw InvalidEntityException(ErrorCode.ARMOR_INVALID_PROTECTION_AREA)
            }
        }
    }

    private fun validPriceAndPz(price: Price, pz: Int) {
        if (pz < 0){
            throw InvalidEntityException(ErrorCode.ARMOR_INVALID_PZ)
        }
    }

    private fun validIfEmpty() {
        if (isBlank(this.name)){
            throw InvalidEntityException(ErrorCode.ARMOR_EMPTY_NAME)
        }
        if (isBlank(this.type.toString())){
            throw InvalidEntityException(ErrorCode.ARMOR_EMPTY_TYPE)
        }
        if (isBlank(this.protectionArea.toString())){
            throw InvalidEntityException(ErrorCode.ARMOR_EMPTY_PROTECTION_AREA)
        }
    }
}