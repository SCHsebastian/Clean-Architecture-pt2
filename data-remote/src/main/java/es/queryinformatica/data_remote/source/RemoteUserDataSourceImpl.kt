package es.queryinformatica.data_remote.source

import es.queryinformatica.data_remote.networking.user.UserService
import es.queryinformatica.data_remote.networking.user.toDomain
import es.queryinformatica.data_repository.data_source.remote.RemoteUserDataSource
import es.queryinformatica.domain.entity.UseCaseException
import es.queryinformatica.domain.entity.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RemoteUserDataSourceImpl @Inject constructor(private val userService: UserService): RemoteUserDataSource {

    override fun getUsers(): Flow<List<User>> = flow{
        emit(userService.getUsers())
    }.map {users ->
        users.map {userApiModel ->
            userApiModel.toDomain()
        }
    }.catch {
        throw UseCaseException.UserException(it)
    }

    override fun getUser(id: Long): Flow<User> = flow{
        emit(userService.getUserById(id))
    }.map {userApiModel ->
        userApiModel.toDomain()
    }.catch {
        throw UseCaseException.UserException(it)
    }

}