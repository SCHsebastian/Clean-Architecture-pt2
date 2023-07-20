package es.queryinformatica.data_repository.data_source.local

import es.queryinformatica.domain.entity.Interaction
import kotlinx.coroutines.flow.Flow

interface LocalInteractionDataSource {
    fun getInteraction(): Flow<Interaction>
    suspend fun saveInteraction(interaction: Interaction)
}