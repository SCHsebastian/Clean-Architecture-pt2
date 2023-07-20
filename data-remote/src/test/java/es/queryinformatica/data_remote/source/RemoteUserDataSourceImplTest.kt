package es.queryinformatica.data_remote.source

import es.queryinformatica.data_remote.networking.user.UserApiModel
import es.queryinformatica.data_remote.networking.user.UserService
import es.queryinformatica.domain.entity.UseCaseException
import es.queryinformatica.domain.entity.User
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

internal class RemoteUserDataSourceImplTest{
    private val userService: UserService = mock()
    private val userDataSource = RemoteUserDataSourceImpl(userService)

    @ExperimentalCoroutinesApi
    @Test
    fun `getUsers should return a list of users`() = runTest {
        // Given
        val remoteUsers = listOf(UserApiModel(1, "name", "username", "email"))
        val expectedUsers = listOf(User(1, "name", "username", "email"))
        // When
        whenever(userService.getUsers()).thenReturn(remoteUsers)
        // Then
        val result = userDataSource.getUsers().first()
        Assert.assertEquals(expectedUsers, result)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `getUser should return a user`() = runTest {
        // Given
        val id = 1L
        val remoteUser = UserApiModel(id, "name", "username", "email")
        val expectedUser = User(id, "name", "username", "email")
        // When
        whenever(userService.getUserById(id)).thenReturn(remoteUser)
        // Then
        val result = userDataSource.getUser(id).first()
        Assert.assertEquals(expectedUser, result)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `getUsers should throw an exception`() = runTest {

        whenever(userService.getUsers()).thenThrow(RuntimeException())

        userDataSource.getUsers().catch {
            Assert.assertTrue(it is UseCaseException.UserException)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `getUser should throw an exception`() = runTest {

        whenever(userService.getUserById(1)).thenThrow(RuntimeException())

        userDataSource.getUser(1).catch {
            Assert.assertTrue(it is UseCaseException.UserException)
        }
    }
}