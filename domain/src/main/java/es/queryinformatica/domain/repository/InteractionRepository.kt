package es.queryinformatica.domain.repository

import es.queryinformatica.domain.entity.Interaction
import kotlinx.coroutines.flow.Flow

interface InteractionRepository {
    fun getInteraction(): Flow<Interaction>
    fun saveInteraction(interaction: Interaction): Flow<Interaction>
}