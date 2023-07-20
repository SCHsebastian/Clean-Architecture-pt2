package es.queryinformatica.data_local.source

import es.queryinformatica.data_local.db.user.UserDao
import es.queryinformatica.data_local.db.user.UserEntity
import es.queryinformatica.domain.entity.User
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

internal class LocalUserDataSourceImplTest{
    private val userDao: UserDao = mock()
    private val userDataSource = LocalUserDataSourceImpl(userDao)

    @ExperimentalCoroutinesApi
    @Test
    fun `getUsers should return a list of users`() = runTest{
        //Given
        val localUser = listOf(UserEntity(1, "name", "username", "email"))
        val expected = listOf(User(1, "name", "username", "email"))
        //When
        whenever(userDao.getUsers()).thenReturn(flowOf(localUser))
        //Then
        val result = userDataSource.getUsers().first()
        Assert.assertEquals(expected, result)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `addUsers should insert a list of users`() = runTest{
        //Given
        val localUsers = listOf(UserEntity(1, "name", "username", "email"))
        val expected = listOf(User(1, "name", "username", "email"))
        //When
        userDataSource.addUsers(expected)
        //Then
        verify(userDao).insertUsers(localUsers)
    }
}