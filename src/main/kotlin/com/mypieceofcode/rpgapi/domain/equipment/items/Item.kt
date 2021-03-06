package com.mypieceofcode.rpgapi.domain.equipment.items

import com.mypieceofcode.rpgapi.domain.DomainObject
import com.mypieceofcode.rpgapi.domain.custom.Price
import com.mypieceofcode.rpgapi.domain.enums.Availability
import com.mypieceofcode.rpgapi.exceptions.ErrorCode
import com.mypieceofcode.rpgapi.exceptions.InvalidEntityException
import org.apache.commons.lang3.StringUtils.isBlank

data class Item(
        val name: String,
        val description: String,
        val availability: Availability,
        val price: Price,
        val id: String? = null
) : DomainObject() {

    init {
        validate()
    }

    override fun validate() {
        validatePrice(this.price)
        when {
            isBlank(this.name) -> throw InvalidEntityException(ErrorCode.ITEM_EMPTY_NAME)
            isBlank(this.description) -> throw InvalidEntityException(ErrorCode.ITEM_EMPTY_DESCRIPTION)
            isBlank(this.availability.toString()) -> throw InvalidEntityException(ErrorCode.ITEM_EMPTY_AVAILABILITY)
        }
    }


    private fun validatePrice(price: Price) {

    }

    companion object {

    }

}