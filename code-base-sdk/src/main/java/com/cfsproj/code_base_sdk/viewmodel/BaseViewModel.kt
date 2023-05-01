package com.cfsproj.code_base_sdk.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import com.cfsproj.code_base_sdk.domain.DomainChar
import com.cfsproj.code_base_sdk.usecases.CharactersUseCase
import com.cfsproj.code_base_sdk.utils.AppType
import com.cfsproj.code_base_sdk.utils.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BaseViewModel @Inject constructor(
    private val charactersUseCase: CharactersUseCase
) : ViewModel() {

    private val _characters: MutableStateFlow<UIState<List<DomainChar>>> =
        MutableStateFlow(UIState.Loading)
    val characters: StateFlow<UIState<List<DomainChar>>> get() = _characters

    private var _currentCharacter = MutableLiveData<DomainChar>()
    val currentCharacter: LiveData<DomainChar>
        get() = _currentCharacter

    private val _stringSearch = MutableLiveData<String?>()
    val stringSearch: LiveData<String?>
        get() = _stringSearch

    fun getCharacters(appType: AppType) {
        appType.let {
            viewModelScope.launch {
                charactersUseCase(it).collect {
                    _characters.value = it
                }
            }
        }

    }

    fun updateCurrentMatch(character: DomainChar) {
        _currentCharacter.postValue(character)
    }

    fun searchByString(newText: String?) {
        newText?.let {
            _stringSearch.postValue(newText)
        }
    }

}