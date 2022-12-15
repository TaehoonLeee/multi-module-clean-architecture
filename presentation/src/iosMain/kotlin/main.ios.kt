import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Application
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.example.data.di.startKoin
import com.example.presentation.root.ExampleApp
import com.example.presentation.root.RootComponentImpl
import platform.UIKit.UIViewController

private val rootComponent by lazy {
    startKoin()
    RootComponentImpl(
        DefaultComponentContext(LifecycleRegistry())
    )
}

fun createExampleViewController(): UIViewController = Application("Example") {
    Column {
        Spacer(Modifier.height(100.dp))
        ExampleApp(rootComponent)
    }
}