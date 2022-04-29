package mvi

interface Executor<INTENT, MESSAGE> {

    fun executeIntent(intent: INTENT)

    fun dispatch(message: MESSAGE)

    fun init(output: (MESSAGE) -> Unit)
}

class DefaultExecutor<INTENT, MESSAGE>(
    private val onIntent: Executor<INTENT, MESSAGE>.(INTENT) -> Unit
) : Executor<INTENT, MESSAGE> {

    private var output: ((MESSAGE) -> Unit)? = null

    override fun executeIntent(intent: INTENT) = onIntent(intent)

    override fun dispatch(message: MESSAGE) {
        output?.invoke(message)
    }

    override fun init(output: (MESSAGE) -> Unit) {
        this.output = output
    }

}