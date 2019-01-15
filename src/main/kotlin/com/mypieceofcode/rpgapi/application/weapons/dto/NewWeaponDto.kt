package com.mypieceofcode.rpgapi.application.weapons.dto

import com.mypieceofcode.rpgapi.domain.custom.Price

data class NewWeaponDto(
        val name: String,
        val type: String,
        val category: String,
        val power: String,
        val weaponTraits: List<String>,
        val description: String = "",
        val price: Price = Price(0f, 0f, 0f),
        val weight: Float = 0f,
        val availability: String = "HIGH",
        val url: String = "",
        val range: Float = 0f,
        val reloadTime: String = ""
) {

}