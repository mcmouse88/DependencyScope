package com.mcmouse88.koinscopeapp.core

import android.os.Bundle
import androidx.annotation.MainThread
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment

private const val ARGS = "string_args"

@MainThread
fun Fragment.longArgs(): Lazy<Long> {
    return lazy {
        requireArguments().getLong(ARGS)
    }
}

@Suppress("UnusedReceiverParameter")
@MainThread
fun Fragment.putLongArgs(value: Long): Bundle {
   return bundleOf(ARGS to value)
}