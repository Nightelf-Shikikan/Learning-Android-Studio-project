package com.example.myapplication2.di



import android.app.Application
import com.example.myapplication2.data.repository.RepositoryModule
import com.example.myapplication2.ui.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RepositoryModule::class,
        ViewModelModule::class,
        DatabaseModule::class

    ]
)


interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): Builder

        fun build(): AppComponent
    }

    fun inject(activity: MainActivity)
}