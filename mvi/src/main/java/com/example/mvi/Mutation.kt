package com.example.mvi

typealias Mutation<State> = State.() -> State

fun <State> mutation(mutation: State.() -> State): Mutation<State> = mutation

object Mutations {
	fun <T : Any> identity(): Mutation<T> = mutation { this }
}

operator fun <T : Any> Mutation<T>.plus(other: Mutation<T>): Mutation<T> = inner@{
	val result = this@plus(this@inner)
	other.invoke(result)
}