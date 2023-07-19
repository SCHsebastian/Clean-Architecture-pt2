package es.queryinformatica.domain.usecase

import es.queryinformatica.domain.entity.Result
import es.queryinformatica.domain.entity.UseCaseException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

abstract class UseCase<I : UseCase.Request, O : UseCase.Response>(private val configuration: Configuration) {

    fun execute(request: I) = process(request)
        .map{
            Result.Success(it) as Result<O>
        }.flowOn(configuration.dispatcher)
        .catch{ e ->
            emit(Result.Error(
                    UseCaseException.createFromThrowable(e)
                ))
        }

    internal abstract fun process(request: I): Flow<O>
    class Configuration(val dispatcher: CoroutineDispatcher)
    interface Request
    interface Response

}