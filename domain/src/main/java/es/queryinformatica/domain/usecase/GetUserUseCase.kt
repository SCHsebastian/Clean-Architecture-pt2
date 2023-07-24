package es.queryinformatica.domain.usecase

import es.queryinformatica.domain.entity.User
import es.queryinformatica.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetUserUseCase(
    configuration: Configuration,
    private val userRepository: UserRepository
) : UseCase<GetUserUseCase.Request, GetUserUseCase.Response>(configuration){

        override fun process(request: Request): Flow<Response> {
            return userRepository.getUser(request.userId).map {
                Response(it)
            }
        }
        data class Request(val userId: Long) : UseCase.Request
        data class Response(val user: User) : UseCase.Response
}