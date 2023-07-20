package es.queryinformatica.data_repository.repository

import es.queryinformatica.data_repository.data_source.local.LocalInteractionDataSource
import es.queryinformatica.domain.entity.Interaction
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.kotlin.whenever

internal class InteractionRepositoryImplTest{

    private val localInteractionDataSource = mock<LocalInteractionDataSource>()
    private val repositoryImpl = InteractionRepositoryImpl(localInteractionDataSource)

    @ExperimentalCoroutinesApi
    @Test
    fun testGetInteraction() = runTest {
        val interaction = Interaction(10)
        whenever(localInteractionDataSource.getInteraction()).thenReturn(flowOf(interaction))
        val result = repositoryImpl.getInteraction().first()
        assertEquals(interaction, result)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testSaveInteraction() = runTest {
        val interaction = Interaction(10)
        whenever(localInteractionDataSource.getInteraction()).thenReturn(flowOf(interaction))
        val result = repositoryImpl.saveInteraction(interaction).first()
        verify(localInteractionDataSource).saveInteraction(interaction)
        assertEquals(interaction, result)
    }
}