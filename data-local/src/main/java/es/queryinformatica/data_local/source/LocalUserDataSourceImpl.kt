package es.queryinformatica.data_local.source

import es.queryinformatica.data_local.db.user.UserDao
import es.queryinformatica.data_local.db.user.toDomain
import es.queryinformatica.data_local.db.user.toEntity
import es.queryinformatica.data_repository.data_source.local.LocalUserDataSource
import es.queryinformatica.domain.entity.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalUserDataSourceImpl @Inject constructor(private val userDao: UserDao) : LocalUserDataSource {

    override fun getUsers(): Flow<List<User>> =
        userDao.getUsers().map { users ->
            users.map { user ->
                user.toDomain()
            }
        }

    override suspend fun addUsers(users: List<User>) =
        userDao.insertUsers(
            users.map {user ->
                user.toEntity()
            }
        )

}