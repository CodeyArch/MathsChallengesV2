package me.goobydev.mathschallengesv2.feature_maths_games.presentation.game_play

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import me.goobydev.mathschallengesv2.feature_maths_games.domain.model.MathsGameMode
import me.goobydev.mathschallengesv2.feature_maths_games.domain.use_cases.GameModesUseCases
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class GamePlayViewModel @Inject constructor (
    private val gameModesUseCases: GameModesUseCases,
    savedStateHandle: SavedStateHandle,
    ) : ViewModel() {
    private val _state = mutableStateOf(GamePlayState())
    val state: MutableState<GamePlayState> = _state

    private val _score = mutableStateOf(0)
    val score: MutableState<Int> = _score

    private val _questionsAnswered = mutableStateOf(0)
    val questionsAnswered: MutableState<Int> = _questionsAnswered

    private val _time = mutableStateOf(0)
    val time: MutableState<Int> = _time

    private val _correctAnswer = mutableStateOf(0)
    val correctAnswer: MutableState<Int> = _correctAnswer

    private val _question = mutableStateOf("")
    val question: MutableState<String> = _question

    private val _answersList = mutableStateOf(listOf(4,4,4,4))
    val answersList: MutableState<List<Int>> = _answersList
    // Shuffle the list to put the answers in random indexes

    private val maxNums = 12 // later change this to use a set number
    private var bestScore = 0
    private var bestTime = 0

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        savedStateHandle.get<Int>("id")?.let { id ->
            viewModelScope.launch {
                _state.value = state.value.copy(
                    gameMode = gameModesUseCases.getGameMode(id) ?: MathsGameMode("", "", "", 0, 0, 0, 0, 1)
                )
                bestScore = state.value.gameMode.score
                bestTime = state.value.gameMode.time
                println(state.value)
                println("Best score is: $bestScore")
                println("Best time is: $bestTime")
                onEvent(GamePlayEvent.GenerateNewQuestion)
            }
        }

    }
    private fun generateRandomNums(maxNums: Int): List<Int> { // maxNums to allow for expansion
        val num1 = Random.nextInt(1, maxNums + 1)
        val num2 = Random.nextInt(1, maxNums + 1)
        return listOf(num1, num2)
    }
    private fun generateAnswersList(answer: Int): List<Int> {
        val answersList = mutableListOf(answer,
            answer + Random.nextInt(2, 5),
            answer + Random.nextInt(5, 10),
            if (answer > 1) {
                answer - Random.nextInt(1, answer)
            } else {
                answer + Random.nextInt(10, 16)
            }).shuffled()
        return answersList
    }
    private fun addition() {
        val (num1, num2) = generateRandomNums(maxNums)
        val answer = num1 + num2
        val question = "$num1 + $num2 = ?"
        _correctAnswer.value = answer
        _question.value = question
        val answersList = generateAnswersList(answer)
        _answersList.value = answersList
        println("Question: ${_question.value}")
        println("Answers: ${_answersList.value}")
    }
    private fun subtraction() {
        var (num1, num2) = generateRandomNums(maxNums)
        if (num2 > num1) {
            num2 = if (num1 - 1 == 0) {
                num1
            } else num1 - 1
        }
        val answer = num1 - num2
        val question = "$num1 - $num2 = ?"
        _correctAnswer.value = answer
        _question.value = question
        val answersList = generateAnswersList(answer)
        _answersList.value = answersList
        println("Question: ${_question.value}")
        println("Answers: ${_answersList.value}")
    }
    private fun multiplication() {
        val (num1, num2) = generateRandomNums(maxNums)
        val answer = num1 * num2
        val question = "$num1 x $num2 = ?"
        _correctAnswer.value = answer
        _question.value = question
        val answersList = generateAnswersList(answer)
        _answersList.value = answersList
        println("Question: ${_question.value}")
        println("Answers: ${_answersList.value}")
    }
    // Division is not supposed to have decimals in our case
    private fun division() {
        var (num1, num2) = generateRandomNums(maxNums)
        while (num1 % num2 != 0) {
            num1 = generateRandomNums(maxNums)[0]
            num2 = generateRandomNums(maxNums)[1]
        }
        val answer = (num1 / num2.toDouble()).toInt() // pain
        val question = "$num1 ÷ $num2 = ?"
        _correctAnswer.value = answer
        _question.value = question
        val answersList = generateAnswersList(answer)
        _answersList.value = answersList
        println("Question: ${_question.value}")
        println("Answers: ${_answersList.value}")
    }

    fun onEvent(event: GamePlayEvent) {
        when(event) {
            is GamePlayEvent.AddToScore -> {
                _score.value = score.value + event.value
                val gameMode = MathsGameMode(
                    state.value.gameMode.gameMode,
                    state.value.gameMode.gameModeSubType,
                    state.value.gameMode.gameModeDifficulty,
                    score.value,
                    state.value.gameMode.maximumScore,
                    state.value.gameMode.time,
                    state.value.gameMode.maximumTime,
                    state.value.gameMode.id
                )
                _state.value = state.value.copy(gameMode = gameMode)
                println("Total score: ${_score.value}")
            }
            is GamePlayEvent.AddToTime -> {
                viewModelScope.launch {
                    _time.value = time.value + event.value
                    val gameMode = MathsGameMode(
                        state.value.gameMode.gameMode,
                        state.value.gameMode.gameModeSubType,
                        state.value.gameMode.gameModeDifficulty,
                        state.value.gameMode.score,
                        state.value.gameMode.maximumScore,
                        time.value,
                        state.value.gameMode.maximumTime,
                        state.value.gameMode.id
                    )
                    _state.value = state.value.copy(gameMode = gameMode)
                    if (_time.value == state.value.gameMode.maximumTime) {
                        _eventFlow.emit(UiEvent.TimerEnd)
                    }
                    println("Total time: ${_time.value}")
                }

            }
            is GamePlayEvent.QuestionAnswered -> {
                // Here we add an if statement i.e if answer is correct then add to score
                viewModelScope.launch {
                    if (event.answer == correctAnswer.value) {
                        onEvent(GamePlayEvent.AddToScore(1))
                    }
                    _questionsAnswered.value = questionsAnswered.value + event.value
                    if (_questionsAnswered.value == state.value.gameMode.maximumScore) {
                        onEvent(GamePlayEvent.UpdateGameMode)
                        _eventFlow.emit(UiEvent.AllQuestionsAnswered)
                    } else {
                        onEvent(GamePlayEvent.GenerateNewQuestion)
                    }
                    println("Questions answered: ${_questionsAnswered.value}")
                }
            }
            GamePlayEvent.GenerateNewQuestion -> {
                when(state.value.gameMode.gameModeSubType){
                    "Addition" -> addition()
                    "Subtraction" -> subtraction()
                    "Multiplication" -> multiplication()
                    "Division" -> division()
                    "Mixed" -> {
                        when (Random.nextInt(1, 5)) {
                            1 -> addition()
                            2 -> subtraction()
                            3 -> multiplication()
                            4 -> division()
                            else -> println("Magic") // Seriously how
                        }
                    }
                }
            }
            GamePlayEvent.UpdateGameMode -> {
                val score = state.value.gameMode.score
                val time = state.value.gameMode.time
                if (score > bestScore || score >= bestScore && bestTime >= time || bestTime == 0) {
                    viewModelScope.launch {
                        gameModesUseCases.updateGameMode(
                            MathsGameMode(
                                state.value.gameMode.gameMode,
                                state.value.gameMode.gameModeSubType,
                                state.value.gameMode.gameModeDifficulty,
                                state.value.gameMode.score,
                                state.value.gameMode.maximumScore,
                                state.value.gameMode.time,
                                state.value.gameMode.maximumTime,
                                state.value.gameMode.id
                            )
                        )
                        println("Hello")
                    }
                }
            }
        }
    }
    sealed class UiEvent {
        object TimerEnd: UiEvent()
        object AllQuestionsAnswered: UiEvent()
    }
}

