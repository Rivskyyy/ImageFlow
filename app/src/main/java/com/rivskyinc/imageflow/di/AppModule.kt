package com.rivskyinc.imageflow.di

import android.app.Application
import androidx.room.Room
import com.rivskyinc.imageflow.Utils.Const.BASE_URL
import com.rivskyinc.imageflow.data.ImageApi
import com.rivskyinc.imageflow.data.db.AppDatabase
import com.rivskyinc.imageflow.data.db.ImageDao
import com.rivskyinc.imageflow.data.repositoryImpl.ImageRepositoryImpl
import com.rivskyinc.imageflow.domain.UseCases.GetImageUseCase
import com.rivskyinc.imageflow.domain.ImageRepository
import com.rivskyinc.imageflow.domain.UseCases.GetImageDetailUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMyApi(): ImageApi {

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ImageApi::class.java)
    }

    @Provides
    fun providesRepository(api: ImageApi): ImageRepository {
        return ImageRepositoryImpl(api)
    }

    @Provides
    fun providesGetImageUseCase(repository: ImageRepository): GetImageUseCase {

        return GetImageUseCase(repository)
    }

    @Provides
    fun providesGetImageDetailUseCase(repository: ImageRepository) : GetImageDetailUseCase{
        return GetImageDetailUseCase(repository)
    }
    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "app_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideDao(appDatabase: AppDatabase): ImageDao {
        return appDatabase.imageDao()
    }

}