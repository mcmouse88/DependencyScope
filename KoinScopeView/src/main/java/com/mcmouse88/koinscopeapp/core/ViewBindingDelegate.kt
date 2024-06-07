package com.mcmouse88.koinscopeapp.core

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import kotlin.reflect.KProperty

inline fun <reified B : ViewBinding> Fragment.viewBinding(): ViewBindingDelegate<B> {
    return ViewBindingDelegate(this, B::class.java)
}

class ViewBindingDelegate<B : ViewBinding>(
    private val fragment: Fragment,
    private val viewBindingClass: Class<B>
) {
    private var binding: B? = null

    operator fun getValue(thisRef: Any?, property: KProperty<*>): B {
        val owner = fragment.viewLifecycleOwner
        return when {
            owner.lifecycle.currentState == Lifecycle.State.DESTROYED -> {
                error("Called after onDestroyView()")
            }

            fragment.view != null -> getOrCreateBinding(owner)
            else -> error("Called before onViewCreated()")
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun getOrCreateBinding(owner: LifecycleOwner): B {
        return this.binding ?: let {
            val method = viewBindingClass.getMethod("bind", View::class.java)
            val binding = method.invoke(null, fragment.view) as B

            owner.lifecycle.addObserver(object : DefaultLifecycleObserver {
                override fun onDestroy(owner: LifecycleOwner) {
                    super.onDestroy(owner)
                    this@ViewBindingDelegate.binding = null
                    owner.lifecycle.removeObserver(this)
                }
            })

            this.binding = binding
            binding
        }
    }
}