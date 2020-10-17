package com.example.noteto_do.di

import android.content.Context
import com.example.noteto_do.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun providesDatabase(
        @ApplicationContext context: Context
    )=AppDatabase.getInstance(context)

    @Singleton
    @Provides
    fun providesToDoDao(db: AppDatabase)=db.toDoDao

}