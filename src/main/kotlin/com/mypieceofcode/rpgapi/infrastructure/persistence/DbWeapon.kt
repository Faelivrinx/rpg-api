package com.mypieceofcode.rpgapi.infrastructure.persistence

import com.mypieceofcode.rpgapi.domain.equipment.weapons.Weapon
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "weapon")
data class DbWeapon(
        val name: String,
        val type: String,
        val category: String,
        val power: String,
        val weaponTraits: List<String>,
        val description: String,
        val price: Float,
        val weight: Float,
        val availability: String,
        val url: String,
        val range: Float? = null,
        val reloadTime: String? = null,
        @Id val id: String? = null
) {

    companion object {
        fun fromWeapon(weapon: Weapon) = DbWeapon(
                weapon.name,
                weapon.type.toString(),
                weapon.category.toString(),
                weapon.power,
                weapon.weaponTraits,
                weapon.description,
                weapon.price,
                weapon.weight,
                weapon.availability.toString(),
                weapon.url,
                weapon.range,
                weapon.reloadTime,
                weapon.id)
    }
}