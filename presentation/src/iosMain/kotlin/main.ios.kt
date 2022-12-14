import androidx.compose.ui.window.Application
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.example.data.di.startKoin
import com.example.presentation.root.ExampleApp
import com.example.presentation.root.RootComponentImpl
import platform.UIKit.UIViewController

private val rootComponent by lazy {
    startKoin {  }
    RootComponentImpl(
        DefaultComponentContext(LifecycleRegistry())
    )
}

fun createExampleViewController(): UIViewController = Application("Example") {
    ExampleApp(rootComponent)
}