package es.queryinformatica.data_repository.repository

import es.queryinformatica.data_repository.data_source.local.LocalPostDataSource
import es.queryinformatica.data_repository.data_source.remote.RemotePostDataSource
import es.queryinformatica.domain.entity.Post
import es.queryinformatica.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

class PostRepositoryImpl (
    private val remotePostDataSource: RemotePostDataSource,
    private val localPostDataSource: LocalPostDataSource
) : PostRepository {

    override fun getPosts(): Flow<List<Post>> =
        remotePostDataSource.getPosts()
            .onEach {
                localPostDataSource.addPosts(it)
            }

    override fun getPost(postId: Long): Flow<Post> =
        remotePostDataSource.getPost(postId)
            .onEach {
                localPostDataSource.addPosts(listOf(it))
            }
}