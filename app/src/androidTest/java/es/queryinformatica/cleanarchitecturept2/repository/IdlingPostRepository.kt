package es.queryinformatica.cleanarchitecturept2.repository

import es.queryinformatica.cleanarchitecturept2.attachIdling
import es.queryinformatica.cleanarchitecturept2.idling.ComposeCountingIdlingResource
import es.queryinformatica.domain.entity.Post
import es.queryinformatica.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow

class IdlingPostRepository(
    private val postRepository: PostRepository,
    private val countingIdlingResource: ComposeCountingIdlingResource
) : PostRepository {

    override fun getPosts(): Flow<List<Post>> {
        return postRepository.getPosts().attachIdling(countingIdlingResource)
    }

    override fun getPost(postId: Long): Flow<Post> {
        return postRepository.getPost(postId).attachIdling(countingIdlingResource)
    }


}
