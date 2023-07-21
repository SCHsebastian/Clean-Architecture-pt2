package es.queryinformatica.data_remote.networking.post

import retrofit2.http.GET
import retrofit2.http.Path

interface PostService {
    @GET("/posts")
    suspend fun getPosts(): List<PostApiModel>

    @GET("/posts/{postId}")
    suspend fun getPostById(@Path("postId") postId: Long): PostApiModel
}