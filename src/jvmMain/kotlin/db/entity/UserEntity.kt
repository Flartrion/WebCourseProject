package db.entity

import dataType.User
import db.model.Users
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

class UserEntity(id: EntityID<UUID>) : Entity<UUID>(id) {
    companion object : EntityClass<UUID, UserEntity>(Users)

    var role by Users.role
    var fullName by Users.fullName
    var dob by Users.dob
    var phoneNumber by Users.phoneNumber
    var email by Users.email

    fun entityToUser(): User = User(
        id_user = id.value.toString(),
        role = role,
        full_name = fullName,
        date_of_birth = dob.toString(),
        phone_number = phoneNumber,
        email = email
    )
}