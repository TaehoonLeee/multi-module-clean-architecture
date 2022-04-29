package mvi

interface Executor<INTENT, MESSAGE> {

    fun executeIntent(intent: INTENT)

    fun dispatch(message: MESSAGE)

    fun init(output: MessageOutput<MESSAGE>)

    fun interface MessageOutput<MESSAGE> {
        fun onMessage(message: MESSAGE)
    }
}

open class DefaultExecutor<INTENT, MESSAGE>(
    private val onIntent: Executor<INTENT, MESSAGE>.(INTENT) -> Unit
) : Executor<INTENT, MESSAGE> {

    private var output: Executor.MessageOutput<MESSAGE>? = null

    override fun executeIntent(intent: INTENT) = onIntent(intent)

    override fun dispatch(message: MESSAGE) {
        output?.onMessage(message)
    }

    override fun init(output: Executor.MessageOutput<MESSAGE>) {
        this.output = output
    }

}