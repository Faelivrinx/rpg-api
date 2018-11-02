package com.mypieceofcode.rpgapi.application.weapons.dto


data class UpdateWeaponDto(
        val id: String,
        val name: String,
        val type: String,
        val category: String,
        val power: String,
        val weaponTraits: List<String>,
        val description: String ,
        val price: Float ,
        val weight: Float ,
        val availability: String ,
        val url: String ,
        val range: Float = 0f,
        val reloadTime: String = ""
) {

}