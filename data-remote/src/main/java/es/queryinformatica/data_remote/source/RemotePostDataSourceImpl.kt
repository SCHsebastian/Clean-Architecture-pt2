package es.queryinformatica.data_remote.source

import es.queryinformatica.data_remote.networking.post.PostService
import es.queryinformatica.data_remote.networking.post.toDomain
import es.queryinformatica.data_repository.data_source.remote.RemotePostDataSource
import es.queryinformatica.domain.entity.Post
import es.queryinformatica.domain.entity.UseCaseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RemotePostDataSourceImpl @Inject constructor(private val postService: PostService) : RemotePostDataSource {
    override fun getPosts(): Flow<List<Post>> = flow{
        emit(postService.getPosts())
    }.map { posts ->
        posts.map { post ->
            post.toDomain()
        }
    }.catch {
        throw UseCaseException.PostException(it)
    }

    override fun getPost(id: Long): Flow<Post> = flow{
        emit(postService.getPostById(id))
    }.map { post ->
        post.toDomain()
    }.catch {
        throw UseCaseException.PostException(it)
    }

}