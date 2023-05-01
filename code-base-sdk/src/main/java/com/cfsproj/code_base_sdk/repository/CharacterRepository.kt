package com.cfsproj.code_base_sdk.repository

import com.cfsproj.code_base_sdk.domain.DomainChar
import com.cfsproj.code_base_sdk.domain.mapToDomain
import com.cfsproj.code_base_sdk.rest.ServiceApi
import com.cfsproj.code_base_sdk.utils.AppType
import com.cfsproj.code_base_sdk.utils.UIState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import javax.inject.Inject

interface CharacterRepository {
    fun getCharacters(type: AppType): Flow<UIState<List<DomainChar>>>
}

class CharactersRepositoryImpl @Inject constructor(
    private val serviceApi: ServiceApi,
    private val ioDispatcher: CoroutineDispatcher
): CharacterRepository {
    override fun getCharacters(type: AppType): Flow<UIState<List<DomainChar>>> = flow {
        emit(UIState.Loading)
        try {
            val response = serviceApi.getCharacters(type.endPoint)
            if (response.isSuccessful){
                response.body()?.let {
                    emit(UIState.Success(response.body()?.relatedTopics.mapToDomain()))
                }
            }
        }catch (httpException: HttpException){
            emit(UIState.Error(httpException))
        }catch (e: Exception){
            emit(UIState.Error(e))
        }
    }.flowOn(ioDispatcher)

}