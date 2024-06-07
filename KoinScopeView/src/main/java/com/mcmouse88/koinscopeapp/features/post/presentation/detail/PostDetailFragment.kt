package com.mcmouse88.koinscopeapp.features.post.presentation.detail

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
import com.mcmouse88.koinscopeapp.databinding.FragmentPostDetailBinding
import com.mcmouse88.koinscopeapp.features.post.di.PostFeature
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class PostDetailFragment : FeatureScopeFragment<PostFeature>(
    contentLayoutId = R.layout.fragment_post_detail,
    feature = PostFeature
) {

    private val binding by viewBinding<FragmentPostDetailBinding>()
    private val args by longArgs()
    private val viewModel by viewModel<PostDetailViewModel> { parametersOf(args) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }

        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.postDetailFlow.collect {

                }
            }
        }
    }
}