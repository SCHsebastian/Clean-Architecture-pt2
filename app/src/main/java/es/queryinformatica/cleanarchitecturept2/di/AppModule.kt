package es.queryinformatica.cleanarchitecturept2.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.queryinformatica.domain.usecase.UseCase
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    fun provideUseCaseConfiguration() =
        UseCase.Configuration(Dispatchers.IO)
}