package com.mypieceofcode.rpgapi.infrastructure.persistence

import com.mypieceofcode.rpgapi.domain.equipment.items.Item
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "item")
data class DbItem(
        val name: String,
        val description: String,
        val price: Float,
        val availability: String,
        @Id val id: String? = null
)  {


    companion object {
        fun toItem(db: DbItem) = Item(db.name,
                db.description,
                db.availability,
                db.price,
                db.id)

        fun fromItem(item: Item) = DbItem(item.name, item.description, item.price, item.availability.toString(), item.id)
    }
}