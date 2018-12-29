package com.mypieceofcode.rpgapi.infrastructure.persistence

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
        val table: List<Pair<String, List<String>>>? = null,
        @Id val id: String? = null
) {

    companion object {
        fun from (db: DbMutation) = Mutation(
                db.name,
                MutationType.createType(db.type),
                db.description,
                db.godType,
                MutationTable(db.table?.toMap().orEmpty(), db.table?.map { it.second.size }.orEmpty().size),
                db.id
        )
        fun to(mutation: Mutation) = DbMutation(
                mutation.name,
                mutation.type.toString(),
                mutation.description,
                mutation.godType,
                MutationTable.from(mutation.table),
                mutation.id
        )
    }
}