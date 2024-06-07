package com.mcmouse88.koinscopeapp.features.user.presentation.list

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
import com.mcmouse88.koinscopeapp.databinding.FragmentUserListBinding
import com.mcmouse88.koinscopeapp.features.user.di.UserFeature
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserListFragment : FeatureScopeFragment<UserFeature>(
    contentLayoutId = R.layout.fragment_user_list,
    feature = UserFeature
) {

    private val binding by viewBinding<FragmentUserListBinding>()
    private val viewModel by viewModel<UserListViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.userListFlow.collect {
                    println(it.joinToString())
                }
            }
        }

        binding.btnToDetail.setOnClickListener {
            findNavController().navigate(
                R.id.action_userListFragment_to_userDetailFragment,
                putLongArgs(1L)
            )
        }

        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onDestroy() {
        Log.e("TAG_CHECK", "onDestroy")
        super.onDestroy()
    }
}