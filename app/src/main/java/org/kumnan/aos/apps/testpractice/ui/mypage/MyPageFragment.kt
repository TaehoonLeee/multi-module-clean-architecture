package org.kumnan.aos.apps.testpractice.ui.mypage

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.kumnan.aos.apps.testpractice.R
import org.kumnan.aos.apps.testpractice.databinding.FragMyPageBinding
import org.kumnan.aos.apps.testpractice.ui.base.BaseFragment

@AndroidEntryPoint
class MyPageFragment : BaseFragment<FragMyPageBinding>(R.layout.frag_my_page) {

    private val userCountViewModel: UserCountViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = userCountViewModel
    }
}