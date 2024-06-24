package com.example.composepagging3.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import coil.network.HttpException
import com.example.composepagging3.data.local.BeerDatabase
import com.example.composepagging3.data.local.BeerEntity
import com.example.composepagging3.data.mappers.toBeerEntity
import okio.IOException


@OptIn(ExperimentalPagingApi::class)
class BeerRemoteMediator(
    private val beerDb : BeerDatabase,
    private val beerApi : BeerApi
) : RemoteMediator<Int,BeerEntity>(){


    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, BeerEntity>
    ): MediatorResult {
        return try {
            val loadKey = when(loadType){
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null){
                        1
                    }else{
                        //Like if our pageSize is 20 and our last item is 80 and then we divide it 80 / 20 which is 4 and we add 1 to get to the next page
                        (lastItem.id / state.config.pageSize) + 1
                    }
                }
            }
            val beers = beerApi.getBeers(
                page = loadKey,
                pageCount = state.config.pageSize
            )

            //The reason why we are using the withTransaction cause we want all our sql operations to be succeed it one failed the whole block will not be executed
            beerDb.withTransaction {
                if (loadType == LoadType.REFRESH){
                    beerDb.beerDao.clearAll()
                }
                val beerEntities = beers.map { it.toBeerEntity() }
                beerDb.beerDao.upsertAll(beerEntities)
            }

            MediatorResult.Success(
                endOfPaginationReached = beers.isEmpty()
            )
        }catch (e : IOException){
            MediatorResult.Error(e)
        }catch (e : HttpException){
            MediatorResult.Error(e)
        }
    }

}











