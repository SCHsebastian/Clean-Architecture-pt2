package es.queryinformatica.data_repository.repository

import es.queryinformatica.data_repository.data_source.local.LocalUserDataSource
import es.queryinformatica.data_repository.data_source.remote.RemoteUserDataSource
import es.queryinformatica.domain.entity.User
import es.queryinformatica.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

class UserRepositoryImpl (
    private val remoteUserDataSource: RemoteUserDataSource,
    private val localUserDataSource: LocalUserDataSource
) : UserRepository {

    override fun getUsers(): Flow<List<User>> =
        remoteUserDataSource.getUsers()
            .onEach {
                localUserDataSource.addUsers(it)
            }

    override fun getUser(userId: Long): Flow<User> = remoteUserDataSource.getUser(userId)
        .onEach {
            localUserDataSource.addUsers(listOf(it))
        }
}