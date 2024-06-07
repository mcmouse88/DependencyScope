package com.mcmouse88.koinscopeapp.features.post.presentation.post

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.mcmouse88.koinscopeapp.R
import com.mcmouse88.koinscopeapp.core.FeatureScopeFragment
import com.mcmouse88.koinscopeapp.core.putLongArgs
import com.mcmouse88.koinscopeapp.core.viewBinding
import com.mcmouse88.koinscopeapp.databinding.FragmentPostListBinding
import com.mcmouse88.koinscopeapp.features.post.di.PostFeature
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class PostListFragment : FeatureScopeFragment<PostFeature>(
    contentLayoutId = R.layout.fragment_post_list,
    feature = PostFeature
) {

    private val binding by viewBinding<FragmentPostListBinding>()
    private val viewModel by viewModel<PostListViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.btnToDetail.setOnClickListener {
            findNavController().navigate(
                R.id.action_postListFragment_to_postDetailFragment,
                putLongArgs(1L)
            )
        }

        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.postListFlow.collect {

                }
            }
        }
    }

    override fun onDestroy() {
        Log.e("TAG_CHECK", "onDestroy")
        super.onDestroy()
    }
}