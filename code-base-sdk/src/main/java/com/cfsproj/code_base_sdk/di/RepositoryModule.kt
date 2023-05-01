package com.cfsproj.code_base_sdk.di
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.cfsproj.code_base_sdk.repository.CharacterRepository
import com.cfsproj.code_base_sdk.repository.CharactersRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun providesRepository(
        characterRepository: CharactersRepositoryImpl
    ): CharacterRepository
}