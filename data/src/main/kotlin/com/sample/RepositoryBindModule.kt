package com.sample

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryBindModule {
    @Binds
    fun bindVisionRepository(
        visionRepositoryImpl: VisionRepositoryImpl,
    ): VisionRepository
}
