package mvi

fun interface Reducer<STATE, MESSAGE> {

    fun reduce(state: STATE, message: MESSAGE): STATE
}