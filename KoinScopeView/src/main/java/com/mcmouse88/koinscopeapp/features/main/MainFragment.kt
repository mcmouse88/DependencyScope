package com.mcmouse88.koinscopeapp.features.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.mcmouse88.koinscopeapp.R
import com.mcmouse88.koinscopeapp.databinding.FragmentMainBinding
import com.mcmouse88.koinscopeapp.core.viewBinding

class MainFragment : Fragment(R.layout.fragment_main) {

    private val binding by viewBinding<FragmentMainBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnUserFeature.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_userListFragment)
        }

        binding.btnPostFeature.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_postListFragment)
        }
    }
}