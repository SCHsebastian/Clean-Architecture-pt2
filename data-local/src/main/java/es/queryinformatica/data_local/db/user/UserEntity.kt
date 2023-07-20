package es.queryinformatica.data_local.db.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import es.queryinformatica.domain.entity.User

@Entity(tableName = "user")
data class UserEntity(

    @PrimaryKey @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name = "email") val email: String

)

fun UserEntity.toDomain() = User(id, name, username, email)

fun User.toEntity() = UserEntity(id, name, username, email)