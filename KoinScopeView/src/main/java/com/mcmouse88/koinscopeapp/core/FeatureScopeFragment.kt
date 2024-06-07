package com.mcmouse88.koinscopeapp.core

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import org.koin.android.ext.android.getKoin
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.fragmentScope
import org.koin.core.scope.Scope

abstract class FeatureScopeFragment<F : FeatureApi>(
    @LayoutRes contentLayoutId: Int,
    private val feature: F
) : Fragment(contentLayoutId), AndroidScopeComponent {

    override val scope: Scope by fragmentScope()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val manager = getKoin().getFeatureScopeManager(feature)
        Log.e("TAG_CHECK", "onViewCreated: $manager")
        manager.link(scope)
    }
}