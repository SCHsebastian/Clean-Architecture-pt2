package es.queryinformatica.data_local.source

import es.queryinformatica.data_local.db.post.PostDao
import es.queryinformatica.data_local.db.post.PostEntity
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

internal class LocalPostDataSourceImplTest{
    private val postDao: PostDao = mock()
    private val postDataSource = LocalPostDataSourceImpl(postDao)

    @ExperimentalCoroutinesApi
    @Test
    fun `getPosts should return a list of posts`() = runTest{
        //Given
        val localPosts = listOf(PostEntity(1, 1, "title", "body"))
        val expected = listOf(Post(1, 1, "title", "body"))
        //When
        whenever(postDao.getPosts()).thenReturn(flowOf(localPosts))
        //Then
        val result = postDataSource.getPosts().first()
        Assert.assertEquals(expected, result)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `addPosts should insert a list of posts`() = runTest{
        //Given
        val localPosts = listOf(PostEntity(1, 1, "title", "body"))
        val expected = listOf(Post(1, 1, "title", "body"))
        //When
        postDataSource.addPosts(expected)
        //Then
        verify(postDao).insertPosts(localPosts)
    }
}