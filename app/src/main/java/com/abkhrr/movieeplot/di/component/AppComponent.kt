package com.abkhrr.movieeplot.di.component

import android.app.Application
import com.abkhrr.movieeplot.di.app.CoreApplication
import com.abkhrr.movieeplot.di.builder.ActivityBuilder
import com.abkhrr.movieeplot.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, ActivityBuilder::class])
interface AppComponent {

    fun inject(app: CoreApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}