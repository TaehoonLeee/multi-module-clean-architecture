package com.example.presentation.ui.mypage

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.presentation.R
import com.example.presentation.databinding.FragMyPageBinding
import dagger.hilt.android.AndroidEntryPoint
import com.example.presentation.ui.base.BaseFragment

@AndroidEntryPoint
class MyPageFragment : BaseFragment<FragMyPageBinding>(R.layout.frag_my_page) {

    private val userCountViewModel: UserCountViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = userCountViewModel
    }
}