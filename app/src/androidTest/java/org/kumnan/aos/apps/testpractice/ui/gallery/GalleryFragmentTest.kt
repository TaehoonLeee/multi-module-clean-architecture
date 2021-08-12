package org.kumnan.aos.apps.testpractice.ui.gallery

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.kumnan.aos.apps.data.entity.UnsplashResponse
import org.kumnan.aos.apps.data.mapper.ResponseMapper
import org.kumnan.aos.apps.data.module.DataModule
import org.kumnan.aos.apps.data.network.UnsplashService
import org.kumnan.aos.apps.data.repository.UnsplashRepositoryImpl
import org.kumnan.aos.apps.domain.entity.UnsplashPhoto
import org.kumnan.aos.apps.domain.entity.status.Result
import org.kumnan.aos.apps.domain.repository.UnsplashRepository
import org.kumnan.aos.apps.testpractice.R
import org.kumnan.aos.apps.testpractice.util.launchFragmentInHiltContainer
import javax.inject.Singleton

@HiltAndroidTest
@UninstallModules(DataModule::class)
@RunWith(AndroidJUnit4::class)
class GalleryFragmentTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Module
    @InstallIn(SingletonComponent::class)
    class DataModule {

        @Singleton
        @Provides
        fun provideUnsplashService(): UnsplashService {
            return object : UnsplashService {
                private val fakePhotoList = listOf(
                    UnsplashPhoto(
                        id = "1",
                        description = "first",
                        urls = UnsplashPhoto.UnsplashPhotoUrls("","","","",""),
                        user = UnsplashPhoto.UnsplashUser("first_user", "first_user")
                    )
                )

                override suspend fun searchPhotos(
                    query: String,
                    page: Int,
                    perPage: Int
                ): Result<UnsplashResponse> {
                    return Result.Success(UnsplashResponse(fakePhotoList, 1), 200)
                }
            }
        }

        @Singleton
        @Provides
        fun provideUnsplashRepository(
            unsplashService: UnsplashService
        ): UnsplashRepository {
             return UnsplashRepositoryImpl(unsplashService, ResponseMapper::responseToPhotoList)
        }
    }

    @Before
    fun launchScreen() {
        launchFragmentInHiltContainer<GalleryFragment>()
    }

    @Test
    fun isRecyclerViewVisible_OnStart() {
        Espresso.onView(ViewMatchers.withId(R.id.rvPhoto))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}