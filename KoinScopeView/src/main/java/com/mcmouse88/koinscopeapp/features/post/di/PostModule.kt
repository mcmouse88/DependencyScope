package com.mcmouse88.koinscopeapp.features.post.di

import com.mcmouse88.koinscopeapp.core.FeatureApi
import com.mcmouse88.koinscopeapp.features.post.data.remote.PostService
import com.mcmouse88.koinscopeapp.features.post.data.remote.PostServiceImpl
import com.mcmouse88.koinscopeapp.features.post.data.repository.PostRepositoryImpl
import com.mcmouse88.koinscopeapp.features.post.domain.repository.PostRepository
import com.mcmouse88.koinscopeapp.features.post.domain.usecases.GetPostDetailUseCase
import com.mcmouse88.koinscopeapp.features.post.domain.usecases.GetPostListUseCase
import com.mcmouse88.koinscopeapp.features.post.presentation.detail.PostDetailViewModel
import com.mcmouse88.koinscopeapp.features.post.presentation.post.PostListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.core.qualifier.TypeQualifier
import org.koin.dsl.module

data object PostFeature : FeatureApi {
    override val qualifier: TypeQualifier = TypeQualifier(this::class)
}

val postModule = module {
    scope<PostFeature> {
        scoped<PostService> { PostServiceImpl() }
        scoped<PostRepository> { PostRepositoryImpl(postService = get()) }
        factoryOf(::GetPostListUseCase)
        factoryOf(::GetPostDetailUseCase)
        viewModelOf(::PostListViewModel)
        viewModel { params ->
            PostDetailViewModel(
                getPostDetailUseCase = get(),
                id = params.get()
            )
        }
    }
}
