package com.mypieceofcode.rpgapi.domain.equipment.weapons

import com.mypieceofcode.rpgapi.domain.DomainObject
import com.mypieceofcode.rpgapi.domain.custom.Price
import com.mypieceofcode.rpgapi.domain.enums.Availability
import com.mypieceofcode.rpgapi.domain.enums.WeaponCategory
import com.mypieceofcode.rpgapi.domain.enums.WeaponType
import com.mypieceofcode.rpgapi.exceptions.ErrorCode
import com.mypieceofcode.rpgapi.exceptions.InvalidEntityException
import org.apache.commons.lang3.StringUtils.isBlank

data class Weapon(
        val name: String,
        val type: WeaponType,
        val category: WeaponCategory,
        val power: String,
        val weaponTraits: List<String>,
        val description: String,
        val price: Price,
        val weight: Float,
        val availability: Availability,
        val url: String,
        val range: Float? = null,
        val reloadTime: String? = null,
        val id: String? = null
) : DomainObject() {

    init {
        validate()
    }

    override fun validate() {
        validateEmpty()
        validatePrice(this.price)
        validateIsRanged(this.type)
    }

    private fun validateIsRanged(type: WeaponType) {
        if (type == WeaponType.RANGED){
            if (range == null || range == 0f){
                throw InvalidEntityException(ErrorCode.WEAPON_EMPTY_RANGE)
            }
            if (reloadTime == null || isBlank(reloadTime)){
                throw InvalidEntityException(ErrorCode.WEAPON_EMPTY_RELOAD_TIME)
            }
        }
    }

    private fun validatePrice(price: Price) {

    }

    private fun validateEmpty() {
        when{
            isBlank(this.name) -> {throw InvalidEntityException(ErrorCode.WEAPON_EMPTY_NAME)}
            isBlank(this.power) -> {throw InvalidEntityException(ErrorCode.WEAPON_EMPTY_POWER)}
            isBlank(this.category.toString()) -> {throw InvalidEntityException(ErrorCode.WEAPON_EMPTY_CATEGORY)}
            isBlank(this.type.toString()) -> {throw InvalidEntityException(ErrorCode.WEAPON_EMPTY_TYPE)}
        }
    }


}