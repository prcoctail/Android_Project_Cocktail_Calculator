package ru.partyshaker.partyshaker.data.partyShaker.users

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.partyshaker.partyshaker.data.partyShaker.users.UsersRepository
import ru.partyshaker.partyshaker.data.partyShaker.users.impl.UsersRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class UsersModule {

    @Binds
    abstract fun bindsUsersRepository(usersRepositoryImpl: UsersRepositoryImpl): UsersRepository
}