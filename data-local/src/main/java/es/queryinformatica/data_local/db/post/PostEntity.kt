package es.queryinformatica.data_local.db.post

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import es.queryinformatica.domain.entity.Post

@Entity(tableName = "post")
data class PostEntity(

    @PrimaryKey @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "userId") val userId: Long,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "body") val body: String

)

fun PostEntity.toDomain() = Post(id, userId, title, body)

fun Post.toEntity() = PostEntity(id, userId, title, body)