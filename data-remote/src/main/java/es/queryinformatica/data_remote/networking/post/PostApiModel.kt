package es.queryinformatica.data_remote.networking.post

import com.squareup.moshi.Json
import es.queryinformatica.domain.entity.Post

data class PostApiModel(
    @Json(name="id") val id: Long,
    @Json(name="userId") val userId: Long,
    @Json(name="title") val title: String,
    @Json(name="body") val body: String,
)

fun PostApiModel.toDomain() =
    Post(
        id = id,
        userId = userId,
        title = title,
        body = body
    )