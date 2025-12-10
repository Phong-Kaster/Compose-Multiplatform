package org.astronex.olyn.ui.menstruation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.astronex.olyn.data.repository.MenstruationRepository
import org.astronex.olyn.data.repository.SymptomDiaryRepository
import org.astronex.olyn.domain.model.MenstruationDay
import org.astronex.olyn.domain.model.SymptomDiary
import org.astronex.olyn.util.LocalDateUtil
import org.astronex.olyn.util.LocalDateUtil.toEpochDay


class HomeViewModel(
    private val menstruationRepository: MenstruationRepository,
    private val symptomDiaryRepository: SymptomDiaryRepository,
) : ViewModel() {

    private val TAG = this::class.simpleName

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {

    }

     fun insertFakeData() {
        viewModelScope.launch {
            val menstruationDay = MenstruationDay(
                epochDay = LocalDateUtil.now().toEpochDay()
            )
            menstruationRepository.insert(menstruationDay = menstruationDay)
        }
    }

    fun insertFakeSymptomData() {
        println("$TAG - Inserting fake symptom data")
        viewModelScope.launch {
            val symptomDiary = SymptomDiary.generateFakeSymptomDiary()
            symptomDiaryRepository.insert(symptomDiary = symptomDiary)
        }
    }
}