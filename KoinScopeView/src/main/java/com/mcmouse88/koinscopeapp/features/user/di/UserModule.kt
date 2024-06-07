package com.mcmouse88.koinscopeapp.features.user.di

import com.mcmouse88.koinscopeapp.core.FeatureApi
import com.mcmouse88.koinscopeapp.features.user.data.remote.service.UserService
import com.mcmouse88.koinscopeapp.features.user.data.remote.service.UserServiceImpl
import com.mcmouse88.koinscopeapp.features.user.data.repositories.UserRepositoryImpl
import com.mcmouse88.koinscopeapp.features.user.domain.repositories.UserRepository
import com.mcmouse88.koinscopeapp.features.user.domain.usecases.GetUserDetailUseCase
import com.mcmouse88.koinscopeapp.features.user.domain.usecases.GetUserListUseCase
import com.mcmouse88.koinscopeapp.features.user.presentation.detail.UserDetailViewModel
import com.mcmouse88.koinscopeapp.features.user.presentation.list.UserListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.core.qualifier.TypeQualifier
import org.koin.dsl.module

data object UserFeature : FeatureApi {
    override val qualifier: TypeQualifier = TypeQualifier(this::class)
}

val userModule = module {

    scope<UserFeature> {
        scoped<UserService> { UserServiceImpl() }
        scoped<UserRepository> { UserRepositoryImpl(get()) }
        factoryOf(::GetUserListUseCase)
        factoryOf(::GetUserDetailUseCase)
        viewModelOf(::UserListViewModel)
        viewModel { params ->
            UserDetailViewModel(
                getUserDetailUseCase = get(),
                id = params.get()
            )
        }
    }
}