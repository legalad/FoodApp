package com.example.foodapp.di

import android.content.Context
import androidx.room.Room
import com.example.foodapp.data.source.DefaultIngredientRepository
import com.example.foodapp.data.source.IngredientDataSource
import com.example.foodapp.data.source.IngredientRepository
import com.example.foodapp.data.source.local.FoodAppDatabase
import com.example.foodapp.data.source.local.IngredientLocalDataSource
import com.example.foodapp.data.source.remote.IngredientRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class RemoteIngredientDataSource

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class LocalIngredientDataSource

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideIngredientRepository(
        @RemoteIngredientDataSource remoteDataSource: IngredientDataSource,
        @LocalIngredientDataSource localDataSource: IngredientDataSource,
        @IODispatcher ioDispatcher: CoroutineDispatcher
    ): IngredientRepository {
        return DefaultIngredientRepository(remoteDataSource, localDataSource, ioDispatcher)
    }

}

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @RemoteIngredientDataSource
    @Provides
    fun provideIngredientRemoteDataSource(): IngredientDataSource = IngredientRemoteDataSource

    @Singleton
    @LocalIngredientDataSource
    @Provides
    fun provideIngredientLocalDataSource(
        database: FoodAppDatabase,
        @IODispatcher ioDispatcher: CoroutineDispatcher
    ): IngredientDataSource {
        return IngredientLocalDataSource(database.ingredientDao(), ioDispatcher)
    }

}

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): FoodAppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            FoodAppDatabase::class.java,
            "food_app.db"
        ).build()
    }

}