package es.queryinformatica.data_repository.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.queryinformatica.data_repository.repository.InteractionRepositoryImpl
import es.queryinformatica.data_repository.repository.PostRepositoryImpl
import es.queryinformatica.data_repository.repository.UserRepositoryImpl
import es.queryinformatica.domain.repository.InteractionRepository
import es.queryinformatica.domain.repository.PostRepository
import es.queryinformatica.domain.repository.UserRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindsUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository

    @Binds
    abstract fun bindsPostRepository(postRepositoryImpl: PostRepositoryImpl): PostRepository

    @Binds
    abstract fun bindsInteractionRepository(interactionRepositoryImpl: InteractionRepositoryImpl): InteractionRepository

}