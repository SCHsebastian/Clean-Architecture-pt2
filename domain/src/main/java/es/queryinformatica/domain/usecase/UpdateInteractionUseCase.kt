package es.queryinformatica.domain.usecase

import es.queryinformatica.domain.repository.InteractionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UpdateInteractionUseCase @Inject constructor(
    configuration: Configuration,
    private val interactionRepository: InteractionRepository
) : UseCase<UpdateInteractionUseCase.Request, UpdateInteractionUseCase.Response>(configuration) {

    override fun process(request: Request): Flow<Response> {
        return interactionRepository.saveInteraction(request.interaction)
            .map {
                Response(it)
            }
    }
    data class Request(val interaction: es.queryinformatica.domain.entity.Interaction) : UseCase.Request
    data class Response(val interaction: es.queryinformatica.domain.entity.Interaction) : UseCase.Response
}