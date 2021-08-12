package org.kumnan.aos.apps.testpractice.util.fakes

import org.kumnan.aos.apps.domain.entity.UnsplashPhoto

object FakePhotoListHolder {

    val fakePhotoList = listOf(
        UnsplashPhoto(
            id = "1",
            description = "first",
            urls = UnsplashPhoto.UnsplashPhotoUrls("","","","",""),
            user = UnsplashPhoto.UnsplashUser("first_user", "first_user")
        )
    )
}