package com.mypieceofcode.rpgapi.domain.items

import com.mypieceofcode.rpgapi.domain.DomainObject
import com.mypieceofcode.rpgapi.domain.enums.Availability
import com.mypieceofcode.rpgapi.exceptions.ErrorCode
import com.mypieceofcode.rpgapi.exceptions.InvalidEntityException
import com.mypieceofcode.rpgapi.infrastructure.persistence.DbItem
import org.apache.commons.lang3.StringUtils.isBlank

data class Item(
        val name: String,
        val description: String,
        val availability: Availability,
        val price: Float,
        val id: String? = null
) : DomainObject() {

    init {
        validate()
    }

    override fun validate() {
        validatePrice(this.price)
        validateAvailability(this.availability)
        when {
            isBlank(this.name) -> throw InvalidEntityException(ErrorCode.ITEM_EMPTY_NAME)
            isBlank(this.description) -> throw InvalidEntityException(ErrorCode.ITEM_EMPTY_DESCRIPTION)
            isBlank(this.availability.toString()) -> throw InvalidEntityException(ErrorCode.ITEM_EMPTY_AVAILABILITY)
        }
    }

    private fun validateAvailability(availability: Availability) {
        if(availability == Availability.INVALID){
            throw InvalidEntityException(ErrorCode.ITEM_INVALID_AVAILABILITY)
        }
    }

    private fun validatePrice(price: Float) {
        if (price < 0.0f){
            throw InvalidEntityException(ErrorCode.ITEM_INVALID_PRICE)
        }
    }

    companion object {

    }

}