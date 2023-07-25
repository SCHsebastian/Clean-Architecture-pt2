package es.queryinformatica.cleanarchitecturept2.repository

import es.queryinformatica.cleanarchitecturept2.attachIdling
import es.queryinformatica.cleanarchitecturept2.idling.ComposeCountingIdlingResource
import es.queryinformatica.domain.entity.Interaction
import es.queryinformatica.domain.repository.InteractionRepository
import kotlinx.coroutines.flow.Flow

class IdlingInteractionRepository (
    private val interactionRepository: InteractionRepository,
    private val countingIdlingResource: ComposeCountingIdlingResource
        ) : InteractionRepository {

    override fun getInteraction(): Flow<Interaction> {
        return interactionRepository.getInteraction().attachIdling(countingIdlingResource)
    }

    override fun saveInteraction(interaction: Interaction): Flow<Interaction> {
        return interactionRepository.saveInteraction(interaction).attachIdling(countingIdlingResource)
    }
}