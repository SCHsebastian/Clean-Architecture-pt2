package es.queryinformatica.cleanarchitecturept2.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.queryinformatica.domain.repository.InteractionRepository
import es.queryinformatica.domain.repository.PostRepository
import es.queryinformatica.domain.repository.UserRepository
import es.queryinformatica.domain.usecase.GetPostsWithUsersWithInteractionUseCase
import es.queryinformatica.domain.usecase.UseCase
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Provides
    fun provideUseCaseConfiguration() =
        UseCase.Configuration(Dispatchers.IO)

    @Provides
    fun provideGetPostWithUsersWithInteractionUseCase(
        configuration: UseCase.Configuration,
        postRepository: PostRepository,
        userRepository: UserRepository,
        interactionRepository: InteractionRepository
    ) =
        GetPostsWithUsersWithInteractionUseCase(
            configuration = configuration,
            postRepository = postRepository,
            userRepository = userRepository,
            interactionRepository = interactionRepository
        )

    @Provides
    fun provideGetPostUseCase(
        configuration: UseCase.Configuration,
        postRepository: PostRepository
    ) =
        es.queryinformatica.domain.usecase.GetPostUseCase(
            configuration = configuration,
            postRepository = postRepository
        )

    @Provides
    fun provideGetUserUseCase(
        configuration: UseCase.Configuration,
        userRepository: UserRepository
    ) =
        es.queryinformatica.domain.usecase.GetUserUseCase(
            configuration = configuration,
            userRepository = userRepository
        )

    @Provides
    fun provideUpdateInteractionUseCase(
        configuration: UseCase.Configuration,
        interactionRepository: InteractionRepository
    ) =
        es.queryinformatica.domain.usecase.UpdateInteractionUseCase(
            configuration = configuration,
            interactionRepository = interactionRepository
        )
}