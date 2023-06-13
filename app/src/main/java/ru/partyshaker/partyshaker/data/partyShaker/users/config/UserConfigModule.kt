package ru.partyshaker.partyshaker.data.partyShaker.users.config

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class UserConfigModule {

    @Provides
    @Singleton
    fun provideConfig(
        @ApplicationContext context: Context
    ) = UserConfig(context = context)
}