package es.queryinformatica.cleanarchitecturept2.di

import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import es.queryinformatica.cleanarchitecturept2.idling.ComposeCountingIdlingResource
import es.queryinformatica.cleanarchitecturept2.repository.IdlingInteractionRepository
import es.queryinformatica.cleanarchitecturept2.repository.IdlingPostRepository
import es.queryinformatica.cleanarchitecturept2.repository.IdlingUserRepository
import es.queryinformatica.data_repository.data_source.local.LocalInteractionDataSource
import es.queryinformatica.data_repository.data_source.local.LocalPostDataSource
import es.queryinformatica.data_repository.data_source.local.LocalUserDataSource
import es.queryinformatica.data_repository.data_source.remote.RemotePostDataSource
import es.queryinformatica.data_repository.data_source.remote.RemoteUserDataSource
import es.queryinformatica.data_repository.repository.InteractionRepositoryImpl
import es.queryinformatica.data_repository.repository.PostRepositoryImpl
import es.queryinformatica.data_repository.repository.UserRepositoryImpl
import es.queryinformatica.domain.repository.InteractionRepository
import es.queryinformatica.domain.repository.PostRepository
import es.queryinformatica.domain.repository.UserRepository
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RepositoryModule::class]
)
class IdlingRepositoryModule {

    @Singleton
    @Provides
    fun provideIdlingResource(): ComposeCountingIdlingResource = ComposeCountingIdlingResource("repository-idling")

    @Provides
    fun providePostRepository(
        remotePostDataSource: RemotePostDataSource,
        localPostDataSource: LocalPostDataSource,
        countingIdlingResource: ComposeCountingIdlingResource
    ): PostRepository = IdlingPostRepository(
        PostRepositoryImpl(
            remotePostDataSource,
            localPostDataSource
        ),
        countingIdlingResource
    )

    @Provides
    fun provideUserRepository(
        remoteUserDataSource: RemoteUserDataSource,
        localUserDataSource: LocalUserDataSource,
        countingIdlingResource: ComposeCountingIdlingResource
    ): UserRepository = IdlingUserRepository(
        UserRepositoryImpl(
            remoteUserDataSource,
            localUserDataSource
        ),
        countingIdlingResource
    )

    @Provides
    fun provideInteractionRepository(
        interactionDataSource: LocalInteractionDataSource,
        countingIdlingResource: ComposeCountingIdlingResource
    ): InteractionRepository = IdlingInteractionRepository(
        InteractionRepositoryImpl(
            interactionDataSource
        ),
        countingIdlingResource
    )

}