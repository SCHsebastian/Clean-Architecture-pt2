package es.queryinformatica.data_repository.repository

import es.queryinformatica.data_repository.data_source.local.LocalPostDataSource
import es.queryinformatica.data_repository.data_source.remote.RemotePostDataSource
import es.queryinformatica.domain.entity.Post
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.kotlin.whenever

internal class PostRepositoryImplTest{

    private val remotePostDataSource = mock<RemotePostDataSource>()
    private val localPostDataSource = mock<LocalPostDataSource>()
    private val repositoryImpl = PostRepositoryImpl(remotePostDataSource, localPostDataSource)

    @ExperimentalCoroutinesApi
    @Test
    fun testGetPosts() = runTest {
        val posts = listOf(Post(1, 1, "title", "body"))
        whenever(remotePostDataSource.getPosts()).thenReturn(flowOf(posts))
        val result = repositoryImpl.getPosts().first()
        Assert.assertEquals(posts, result)
        verify(localPostDataSource).addPosts(posts)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testGetPost() = runTest {
        val id = 1L
        val post = Post(id, 1, "title", "body")
        whenever(remotePostDataSource.getPost(id)).thenReturn(flowOf(post))
        val result = repositoryImpl.getPost(id).first()
        Assert.assertEquals(post, result)
        verify(localPostDataSource).addPosts(listOf(post))
    }

}