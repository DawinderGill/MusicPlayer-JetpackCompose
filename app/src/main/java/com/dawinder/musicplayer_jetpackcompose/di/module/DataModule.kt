package com.dawinder.musicplayer_jetpackcompose.di.module

import com.dawinder.musicplayer_jetpackcompose.repository.SongRepository
import com.dawinder.musicplayer_jetpackcompose.repository.SongRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    @Singleton
    fun provideSongRepository(songRepository: SongRepositoryImpl): SongRepository {
        return songRepository
    }
}