package com.mypieceofcode.rpgapi.infrastructure.persistence

import com.mypieceofcode.rpgapi.domain.mutations.Gods
import com.mypieceofcode.rpgapi.domain.mutations.Mutation
import com.mypieceofcode.rpgapi.domain.mutations.MutationTable
import com.mypieceofcode.rpgapi.domain.mutations.MutationType
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "mutations")
class DbMutation(
        val name: String,
        val type: String,
        val description: String,
        val godType: String,
        val ps: Int,
        val roll: String,
        val comment: String = "",
        val variants: String = "",
        val table: List<Pair<String, List<String>>>? = null,
        @Id val id: String? = null
) {

    companion object {
        fun from (db: DbMutation) = Mutation(
                db.name,
                MutationType.createType(db.type),
                db.description,
                Gods.createGod(db.godType),
                db.ps,
                db.roll,
                db.comment,
                db.variants,
                MutationTable(db.table?.toMap().orEmpty(), db.table?.map { it.second.size }.orEmpty().size),
                db.id
        )
        fun to(mutation: Mutation) = DbMutation(
                mutation.name,
                mutation.type.toString(),
                mutation.description,
                mutation.godType.toString(),
                mutation.ps,
                mutation.roll,
                mutation.comment,
                mutation.variants,
                MutationTable.from(mutation.table),
                mutation.id
        )
    }
}