package es.queryinformatica.cleanarchitecturept2.di

import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import es.queryinformatica.cleanarchitecturept2.remote.MockRemotePostDataSource
import es.queryinformatica.cleanarchitecturept2.remote.MockRemoteUserDataSource
import es.queryinformatica.data_remote.di.RemoteDataSourceModule
import es.queryinformatica.data_repository.data_source.remote.RemotePostDataSource
import es.queryinformatica.data_repository.data_source.remote.RemoteUserDataSource

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RemoteDataSourceModule::class]
)
abstract class MockRemoteDataSourceModule {

    @Binds
    abstract fun bindPostDataSource(postDataSourceImpl: MockRemotePostDataSource): RemotePostDataSource

    @Binds
    abstract fun bindUserDataSource(userDataSourceImpl: MockRemoteUserDataSource): RemoteUserDataSource

}