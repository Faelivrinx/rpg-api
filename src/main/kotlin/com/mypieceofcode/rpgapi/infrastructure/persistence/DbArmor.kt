package com.mypieceofcode.rpgapi.infrastructure.persistence

import com.mypieceofcode.rpgapi.domain.custom.Price
import com.mypieceofcode.rpgapi.domain.enums.ArmorType.Companion.createArmorType
import com.mypieceofcode.rpgapi.domain.enums.Availability
import com.mypieceofcode.rpgapi.domain.enums.ProtectionArea.Companion.createArea
import com.mypieceofcode.rpgapi.domain.equipment.armors.Armor
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "armor")
data class DbArmor(
        val name: String,
        val type: String,
        val protectionAreas: List<String>,
        val pz: Int,
        val price: Price,
        val weight: Float,
        val availability: String,
        val description: String,
        val url: String,
        @Id val id: String? = null

) {
    companion object {
        fun toArmor(db: DbArmor) = Armor(
                db.name,
                createArmorType(db.type),
                db.protectionAreas.map { createArea(it) },
                db.pz,
                db.price,
                db.weight,
                Availability.createAvailability(db.availability),
                db.description,
                db.url,
                db.id)

        fun fromArmor(armor: Armor) = DbArmor(
                armor.name,
                armor.type.toString(),
                armor.protectionArea.map { it.toString() },
                armor.pz,
                armor.price,
                armor.weight,
                armor.availability.toString(),
                armor.description,
                armor.url,
                armor.id
        )
    }
}