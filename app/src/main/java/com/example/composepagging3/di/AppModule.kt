package com.example.composepagging3.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.example.composepagging3.data.local.BeerDatabase
import com.example.composepagging3.data.local.BeerEntity
import com.example.composepagging3.data.remote.BeerApi
import com.example.composepagging3.data.remote.BeerApi.Companion.BASE_URL
import com.example.composepagging3.data.remote.BeerRemoteMediator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton
import kotlin.reflect.KClass


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun providesBeerDatabase(@ApplicationContext context: Context): BeerDatabase {
        return Room.databaseBuilder(
            context,
            BeerDatabase::class.java,
            "beers.db"
        ).build()
    }


    @Provides
    @Singleton
    fun providesBeerApi(): BeerApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(BeerApi::class.java)
    }

    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun providesBeerPager(beerDb : BeerDatabase, beerApi : BeerApi) : Pager<Int,BeerEntity>{
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = BeerRemoteMediator(
                beerDb = beerDb,
                beerApi = beerApi
            ),
            pagingSourceFactory = {
                beerDb.beerDao.pagingSource()
            }
        )
    }


}



