package es.queryinformatica.data_repository.data_source.remote

import es.queryinformatica.domain.entity.User
import kotlinx.coroutines.flow.Flow

interface RemoteUserDataSource {
    fun getUsers(): Flow<List<User>>
    fun getUser(id: Long): Flow<User>
}