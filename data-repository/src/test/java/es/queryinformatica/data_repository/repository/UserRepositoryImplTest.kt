package es.queryinformatica.data_repository.repository

import es.queryinformatica.data_repository.data_source.local.LocalUserDataSource
import es.queryinformatica.data_repository.data_source.remote.RemoteUserDataSource
import es.queryinformatica.domain.entity.User
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

internal class UserRepositoryImplTest {

    private val remoteUserDataSource = mock<RemoteUserDataSource>()

    private val localUserDataSource = mock<LocalUserDataSource>()

    private val repositoryImpl = UserRepositoryImpl(remoteUserDataSource, localUserDataSource)

    @Test
    fun testGetUsers() = runTest{
        val users = listOf(User(1, "name", "username", "email"))
        whenever(remoteUserDataSource.getUsers()).thenReturn(flowOf(users))
        val result = repositoryImpl.getUsers().first()
        assertEquals(users, result)
        verify(localUserDataSource).addUsers(users)
    }

}