package es.queryinformatica.cleanarchitecturept2.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun providesUserRepository(
        remoteUserDataSource: RemoteUserDataSource,
        localUserDataSource: LocalUserDataSource
    ): UserRepository =
        UserRepositoryImpl(
            remoteUserDataSource = remoteUserDataSource,
            localUserDataSource = localUserDataSource
        )

    @Provides
    fun providesPostRepository(
        remotePostDataSource: RemotePostDataSource,
        localPostDataSource: LocalPostDataSource
    ): PostRepository =
        PostRepositoryImpl(
            remotePostDataSource = remotePostDataSource,
            localPostDataSource = localPostDataSource
        )

    @Provides
    fun providesInteractionRepository(
        localInteractionDataSource: LocalInteractionDataSource
    ): InteractionRepository =
        InteractionRepositoryImpl(
            interactionDataSource = localInteractionDataSource
        )

}