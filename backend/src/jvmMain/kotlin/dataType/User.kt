package dataType

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val idUser: String,
    val role: Int,
    val fullName: String,
    val dateOfBirth: String,
    val phoneNumber: String,
    val email: String
)