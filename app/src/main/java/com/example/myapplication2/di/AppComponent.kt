package com.example.myapplication2.di


import android.app.Application
import android.content.Context
import com.example.myapplication2.data.repository.RepositoryModule
import com.example.myapplication2.ui.MainActivity
import com.example.myapplication2.ui.api.ApiActivity
import com.example.myapplication2.ui.catalog.CatalogActivity
import com.example.myapplication2.ui.status.StatusActivity
import com.example.myapplication2.ui.test1.Test1Activity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RepositoryModule::class,
        ViewModelBindingModule::class,
        DatabaseModule::class,
        ViewModelSetModule::class

    ]
)


interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): Builder

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }


    fun inject(catalogActivity: CatalogActivity)

    fun inject(activity: MainActivity)

    fun inject(activity: Test1Activity)

    fun inject(activity: StatusActivity)

    fun inject(activity: ApiActivity)
}