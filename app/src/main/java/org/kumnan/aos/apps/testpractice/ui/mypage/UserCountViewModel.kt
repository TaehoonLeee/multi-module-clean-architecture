package org.kumnan.aos.apps.testpractice.ui.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import org.kumnan.aos.apps.domain.interactor.GetUserCountUseCase
import org.kumnan.aos.apps.domain.interactor.LogOutUseCase
import javax.inject.Inject

@HiltViewModel
class UserCountViewModel @Inject constructor(
    getUserCountUseCase: GetUserCountUseCase,
    private val logOutUseCase: LogOutUseCase
) : ViewModel() {

    val userCount: LiveData<Int> = getUserCountUseCase().asLiveData()

    override fun onCleared() {
        logOutUseCase.execute()

        super.onCleared()
    }
}
