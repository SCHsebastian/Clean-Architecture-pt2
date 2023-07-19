package es.queryinformatica.domain.repository

import es.queryinformatica.domain.entity.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUsers(): Flow<List<User>>
    fun getUser(userId: Long): Flow<User>
}