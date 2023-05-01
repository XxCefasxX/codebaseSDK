package com.cfsproj.code_base_sdk.usecases

import com.cfsproj.code_base_sdk.domain.DomainChar
import com.cfsproj.code_base_sdk.repository.CharacterRepository
import com.cfsproj.code_base_sdk.utils.AppType
import com.cfsproj.code_base_sdk.utils.UIState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharactersUseCase @Inject constructor(
    private val repository: CharacterRepository
) {
    operator fun invoke(appType: AppType): Flow<UIState<List<DomainChar>>> =
        repository.getCharacters(appType)
}