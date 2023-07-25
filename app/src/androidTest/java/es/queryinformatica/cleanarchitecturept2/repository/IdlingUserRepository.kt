package es.queryinformatica.cleanarchitecturept2.repository

import es.queryinformatica.cleanarchitecturept2.attachIdling
import es.queryinformatica.cleanarchitecturept2.idling.ComposeCountingIdlingResource
import es.queryinformatica.domain.entity.User
import es.queryinformatica.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class IdlingUserRepository(
    private val userRepository: UserRepository,
    private val countingIdlingResource: ComposeCountingIdlingResource
) : UserRepository {
    override fun getUsers(): Flow<List<User>> {
        return userRepository.getUsers().attachIdling(countingIdlingResource)
    }

    override fun getUser(userId: Long): Flow<User> {
        return userRepository.getUser(userId).attachIdling(countingIdlingResource)
    }
}