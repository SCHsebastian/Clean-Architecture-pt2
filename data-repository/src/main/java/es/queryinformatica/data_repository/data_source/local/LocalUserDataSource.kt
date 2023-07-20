package es.queryinformatica.data_repository.data_source.local

import es.queryinformatica.domain.entity.User
import kotlinx.coroutines.flow.Flow

interface LocalUserDataSource {
    fun getUsers(): Flow<List<User>>
    suspend fun addUsers(users: List<User>)
}