package org.kumnan.aos.apps.data.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import org.kumnan.aos.apps.data.network.socket.SocketService
import org.kumnan.aos.apps.data.repository.UserCountRepositoryImpl
import org.kumnan.aos.apps.domain.repository.UserCountRepository

@Module
@InstallIn(ViewModelComponent::class)
object SocketModule {

    @ViewModelScoped
    @Provides
    fun provideSocketService(): SocketService {
        return SocketService()
    }

    @ViewModelScoped
    @Provides
    fun provideUserCountRepository(socketService: SocketService): UserCountRepository {
        return UserCountRepositoryImpl(socketService)
    }
}