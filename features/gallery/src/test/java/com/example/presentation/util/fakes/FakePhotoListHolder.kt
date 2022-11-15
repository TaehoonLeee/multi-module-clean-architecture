package com.example.presentation.util.fakes

import com.example.domain.model.UnsplashPhoto

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