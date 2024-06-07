package com.mcmouse88.koinscopeapp.core

import org.koin.core.Koin
import org.koin.core.component.getScopeId
import org.koin.core.scope.Scope
import org.koin.core.scope.ScopeCallback
import org.koin.core.scope.ScopeID

class FeatureScopeManager<F : FeatureApi>(
    private val koin: Koin,
    private val feature: F
) {

    private val scopeCallback = object : ScopeCallback {
        override fun onScopeClose(scope: Scope) {
            if (childScopes.remove(scope.id)) {
                if (childScopes.isEmpty()) {
                    featureScope.close()
                }
            }
        }
    }

    private val featureScope = createScope()
    private val childScopes = mutableSetOf<ScopeID>()

    fun link(childScope: Scope) {
        if (childScope.id in childScopes) return

        childScopes.add(childScope.id)
        childScope.linkTo(featureScope)
        childScope.registerCallback(scopeCallback)
    }

    private fun createScope(): Scope {
        val qualifier = feature.qualifier
        return koin.getOrCreateScope(qualifier.getScopeId(), qualifier).also { scope ->
            scope.declare(this, qualifier)
        }
    }
}

/**
 * Retrieves the instance of [FeatureScopeManager] associated with a specific feature.
 * Example of retrieving [FeatureScopeManager] in a `Fragment` class:
 * ```
 * override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
 *     super.onViewCreated(view, savedInstanceState)
 *     val featureScopeManager = getKoin().getFeatureScopeManager(ExampleFeature)
 *     // other code
 * }
 * ```
 * @param feature the data object associated with the feature scope module
 * @return The instance of [FeatureScopeManager] associated with the specified feature
 */
fun <F : FeatureApi> Koin.getFeatureScopeManager(feature: F): FeatureScopeManager<F> {
    val scope = getScopeOrNull(feature.qualifier.getScopeId())
    val manager = scope?.getOrNull<FeatureScopeManager<F>>(feature.qualifier)
    return manager ?: FeatureScopeManager(this, feature)
}