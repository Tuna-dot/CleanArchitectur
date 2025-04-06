package com.example.cleanarchitectur.data.page

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.cleanarchitectur.data.datasource.network.CartonApiService
import com.example.cleanarchitectur.data.model.pagemodel.CharacterDto
import retrofit2.HttpException
import java.net.SocketTimeoutException

const val START_INDEX = 1

class ChracterspagengSource(
    private val api: CartonApiService
) : PagingSource<Int, CharacterDto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterDto> {
        return try {
            val currentkey = params.key ?: START_INDEX
            val response = api.getCharacters(currentkey)
            if (response.results?.isNotEmpty() == true) {
                LoadResult.Page(
                    data = response.results,
                    prevKey = if (currentkey == START_INDEX) null else currentkey.minus(1),
                    nextKey = response.info?.next?.let { currentkey.plus(1) }
                )
            } else {
                LoadResult.Error(Exception("Ошибка при получения данных"))
            }

        } catch (e: Exception) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        } catch (e: SocketTimeoutException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CharacterDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}