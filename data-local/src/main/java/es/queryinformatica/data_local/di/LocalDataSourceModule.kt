package es.queryinformatica.data_local.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.queryinformatica.data_local.source.LocalInteractionDataSourceImpl
import es.queryinformatica.data_local.source.LocalPostDataSourceImpl
import es.queryinformatica.data_local.source.LocalUserDataSourceImpl
import es.queryinformatica.data_repository.data_source.local.LocalInteractionDataSource
import es.queryinformatica.data_repository.data_source.local.LocalPostDataSource
import es.queryinformatica.data_repository.data_source.local.LocalUserDataSource

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSourceModule {

    @Binds
    abstract fun bindPostDataSource(lostDataSourceImpl: LocalPostDataSourceImpl): LocalPostDataSource

    @Binds
    abstract fun bindUserDataSource(userDataSourceImpl: LocalUserDataSourceImpl): LocalUserDataSource

    @Binds
    abstract fun bindInteractionDataStore(interactionDataStore: LocalInteractionDataSourceImpl): LocalInteractionDataSource

}