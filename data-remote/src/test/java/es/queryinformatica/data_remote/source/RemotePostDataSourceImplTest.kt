package es.queryinformatica.data_remote.source

import es.queryinformatica.data_remote.networking.post.PostApiModel
import es.queryinformatica.data_remote.networking.post.PostService
import es.queryinformatica.domain.entity.Post
import es.queryinformatica.domain.entity.UseCaseException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

internal class RemotePostDataSourceImplTest{
    private val postService: PostService = mock()
    private val postDataSource = RemotePostDataSourceImpl(postService)

    @ExperimentalCoroutinesApi
    @Test
    fun `getPosts should return a list of posts`() = runTest {
        // Given
        val remotePosts = listOf(PostApiModel(1, 1, "title", "body"))
        val expectedPosts = listOf(Post(1, 1, "title", "body"))
        // When
        whenever(postService.getPosts()).thenReturn(remotePosts)
        // Then
        val result = postDataSource.getPosts().first()
        Assert.assertEquals(expectedPosts, result)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `getPost should return a post`() = runTest {
        // Given
        val id = 1L
        val remotePost = PostApiModel(id, 1, "title", "body")
        val expectedPost = Post(id, 1, "title", "body")
        // When
        whenever(postService.getPostById(1)).thenReturn(remotePost)
        // Then
        val result = postDataSource.getPost(1).first()
        Assert.assertEquals(expectedPost, result)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `getPosts should throw an exception`() = runTest {

        whenever(postService.getPosts()).thenThrow(RuntimeException())

        postDataSource.getPosts().catch {
            Assert.assertTrue(it is UseCaseException.PostException)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `getPost should throw an exception`() = runTest {

        whenever(postService.getPostById(1)).thenThrow(RuntimeException())

        postDataSource.getPost(1).catch {
            Assert.assertTrue(it is UseCaseException.PostException)
        }
    }
}