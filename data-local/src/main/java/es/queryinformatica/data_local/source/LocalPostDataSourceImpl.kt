package es.queryinformatica.data_local.source

import es.queryinformatica.data_local.db.post.PostDao
import es.queryinformatica.data_local.db.post.toDomain
import es.queryinformatica.data_local.db.post.toEntity
import es.queryinformatica.data_repository.data_source.local.LocalPostDataSource
import es.queryinformatica.domain.entity.Post
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalPostDataSourceImpl @Inject constructor(private val postDao: PostDao) : LocalPostDataSource {

    override fun getPosts(): Flow<List<Post>> =
        postDao.getPosts().map { posts ->
            posts.map {post ->
                post.toDomain()
            }
        }

    override suspend fun addPosts(posts: List<Post>) =
        postDao.insertPosts(
            posts.map {post ->
                post.toEntity()
            }
        )

}