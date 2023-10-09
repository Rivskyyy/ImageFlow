package com.rivskyinc.imageflow.di

import android.app.Application
import com.rivskyinc.imageflow.presentation.DetailFragment.DetailFragment
import com.rivskyinc.imageflow.presentation.MainFragment.MainFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface ApplicationComponent {

    fun inject(fragment : MainFragment)

    fun injectInFragmentDetail (fragment: DetailFragment)
    @Component.Factory
    interface Factory {

        fun create (
            @BindsInstance application : Application
        ) : ApplicationComponent
    }
}