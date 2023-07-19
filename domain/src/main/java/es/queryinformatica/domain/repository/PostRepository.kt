package es.queryinformatica.domain.repository

import es.queryinformatica.domain.entity.Post
import kotlinx.coroutines.flow.Flow

interface PostRepository {
    fun getPosts(): Flow<List<Post>>
    fun getPost(postId: Long): Flow<Post>
}