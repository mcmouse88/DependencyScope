package com.mcmouse88.koinscopeapp.core

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mcmouse88.koinscopeapp.R
import org.koin.android.ext.android.getKoin
import org.koin.android.scope.AndroidScopeComponent
import org.koin.core.scope.Scope

class MainActivity : AppCompatActivity(), AndroidScopeComponent {

    override val scope: Scope = getKoin().getOrCreateScope<Any>("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}