package es.queryinformatica.data_local.source

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import es.queryinformatica.domain.entity.Interaction
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.any
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

internal class LocalInteractionDataSourceImplTest{

    private val dataStore: DataStore<Preferences> = mock()
    private val interactionDataSource = LocalInteractionDataSourceImpl(dataStore)

    @ExperimentalCoroutinesApi
    @Test
    fun `getInteraction should return an interaction`() = runTest{
        //Given
        val clicks = 10
        val interaction = Interaction(clicks)
        val preferences:Preferences = mock()
        //When
        whenever(preferences[KEY_TOTAL_TAPS]).thenReturn(clicks)
        whenever(dataStore.data).thenReturn(flowOf(preferences))
        //Then
        val result = interactionDataSource.getInteraction().first()
        Assert.assertEquals(interaction, result)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `saveInteraction should save an interaction`() = runTest {
        //Given
        val clicks = 10
        val interaction = Interaction(clicks)
        val preferences: MutablePreferences = mock()
        //When
        whenever(preferences.toMutablePreferences()).thenReturn(preferences)
        whenever(dataStore.updateData(any())).thenAnswer{
            runBlocking {
                it.getArgument < suspend (Preferences) -> Preferences >(0).invoke(preferences)
            }
            preferences
        }
        //Then
        interactionDataSource.saveInteraction(interaction)
        verify(preferences)[KEY_TOTAL_TAPS] = interaction.totalClicks
    }
}