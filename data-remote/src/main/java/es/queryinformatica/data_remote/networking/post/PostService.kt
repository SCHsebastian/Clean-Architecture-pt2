package es.queryinformatica.data_remote.networking.post

import retrofit2.http.GET

interface PostService {
    @GET("/posts")
    suspend fun getPosts(): List<PostApiModel>

    @GET("/posts/{postId}")
    suspend fun getPostById(postId: Long): PostApiModel
}