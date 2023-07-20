package es.queryinformatica.data_remote.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.queryinformatica.data_remote.source.RemotePostDataSourceImpl
import es.queryinformatica.data_remote.source.RemoteUserDataSourceImpl
import es.queryinformatica.data_repository.data_source.remote.RemotePostDataSource
import es.queryinformatica.data_repository.data_source.remote.RemoteUserDataSource

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourceModule {

    @Binds
    abstract fun bindPostDataSource(postDataSourceImpl: RemotePostDataSourceImpl): RemotePostDataSource

    @Binds
    abstract fun bindUserDataSource(userDataSourceImpl: RemoteUserDataSourceImpl): RemoteUserDataSource

}