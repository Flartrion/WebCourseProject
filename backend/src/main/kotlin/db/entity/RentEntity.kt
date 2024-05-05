package db.entity

import db.model.Rents
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

// TODO: Think again about the app structure. What point is there sending sad single
//       entries when DAO opens up ability to find and transfer entire maps of values?
class RentEntity(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : EntityClass<UUID, RentEntity>(Rents)

    val idUser by UserEntity referencedOn Rents.idUser
    val idItem by ItemEntity referencedOn Rents.idItem
    val idStorage by StorageEntity referencedOn Rents.idStorage
    val dateFrom by Rents.dateFrom
    val dateUntil by Rents.dateUntil


}