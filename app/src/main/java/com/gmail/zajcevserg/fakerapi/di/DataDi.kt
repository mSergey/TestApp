package com.gmail.zajcevserg.fakerapi.di

import android.content.Context
import androidx.room.Room
import com.gmail.zajcevserg.data.datasource.database.AppDatabase
import com.gmail.zajcevserg.data.datasource.database.FakerDataDao
import com.gmail.zajcevserg.data.datasource.service.FakerApiService
import com.gmail.zajcevserg.data.repository.local.FakerDataLocalRepositoryImpl
import com.gmail.zajcevserg.data.repository.remote.FakerRemoteRepositoryImpl
import com.gmail.zajcevserg.domain.repository.FakerDataLocalRepository

import com.gmail.zajcevserg.domain.repository.FakerDataRemoteRepository
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val dataBaseInstanceModule = module {
    fun provideDatabaseInstance(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java,"AppDB")
            .build()
    }

    single<AppDatabase> {
        provideDatabaseInstance(
            context = get()
        )
    }
}
val daoModule = module {

    fun provideFakerDataDao(
        database: AppDatabase
    ): FakerDataDao {
        return database.fakerDataDao
    }

    single<FakerDataDao> {
        provideFakerDataDao(
            database = get()
        )
    }

}

val repositoryModule = module {

    single<FakerDataRemoteRepository> {
        FakerRemoteRepositoryImpl(
            datasource = get()
        )
    }

    single<FakerDataLocalRepository> {
        FakerDataLocalRepositoryImpl(
            datasource = get()
        )
    }

}


val serviceModule = module {

    fun provideFakerApiService(
        retrofit: Retrofit
    ): FakerApiService {
        return retrofit.create(FakerApiService::class.java)
    }

    single<Retrofit> {
       Retrofit.Builder()
            .baseUrl("https://fakerapi.it/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<FakerApiService> {
        provideFakerApiService(
            retrofit = get()
        )
    }
}


