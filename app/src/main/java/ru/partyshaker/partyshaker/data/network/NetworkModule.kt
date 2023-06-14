package ru.partyshaker.partyshaker.data.network

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.partyshaker.partyshaker.data.network.NetworkApi
import ru.partyshaker.partyshaker.data.network.NetworkApiImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    @Binds
    abstract fun bindNetworkApiImpl(networkApiImpl: NetworkApiImpl) : NetworkApi
}