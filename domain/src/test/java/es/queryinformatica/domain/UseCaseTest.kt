package es.queryinformatica.domain

import es.queryinformatica.domain.entity.Result
import es.queryinformatica.domain.usecase.UseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock

class UseCaseTest {
    @ExperimentalCoroutinesApi
    private val configuration = UseCase.Configuration(dispatcher = StandardTestDispatcher())
    private val request = mock<UseCase.Request>()
    private val response = mock<UseCase.Response>()
    @ExperimentalCoroutinesApi
    private lateinit var useCase: UseCase<UseCase.Request, UseCase.Response>
    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        useCase = object : UseCase<UseCase.Request, UseCase.Response>(configuration) {
            override fun process(request: Request): Flow<Response> {
                assertEquals(this@UseCaseTest.request, request)
                return flowOf(response)
            }
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testExecuteSuccess() = runTest {
        val result = useCase.execute(request).first()
        assertEquals(Result.Success(response), result)
    }
}