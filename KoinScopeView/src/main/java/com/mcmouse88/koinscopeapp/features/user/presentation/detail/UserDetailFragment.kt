package com.mcmouse88.koinscopeapp.features.user.presentation.detail

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.mcmouse88.koinscopeapp.R
import com.mcmouse88.koinscopeapp.core.FeatureScopeFragment
import com.mcmouse88.koinscopeapp.core.longArgs
import com.mcmouse88.koinscopeapp.core.viewBinding
import com.mcmouse88.koinscopeapp.databinding.FragmentUserDetailBinding
import com.mcmouse88.koinscopeapp.features.user.di.UserFeature
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class UserDetailFragment : FeatureScopeFragment<UserFeature>(
    contentLayoutId = R.layout.fragment_user_detail,
    feature = UserFeature
) {

    private val binding by viewBinding<FragmentUserDetailBinding>()
    private val args by longArgs()
    private val viewModel by viewModel<UserDetailViewModel> { parametersOf(args) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.userFlow.collect {
                    println(it)
                }
            }
        }

        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}