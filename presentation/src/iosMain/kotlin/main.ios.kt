import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Application
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.example.data.di.dataModule
import com.example.domain.di.domainModule
import com.example.presentation.root.ExampleApp
import com.example.presentation.root.RootComponentImpl
import org.koin.core.context.startKoin
import platform.UIKit.UIViewController

private val rootComponent by lazy {
    RootComponentImpl(
        DefaultComponentContext(LifecycleRegistry())
    )
}

fun createExampleViewController(): UIViewController {
    startKoin {
        modules(dataModule + domainModule)
    }

    return Application("Example") {
        Column {
            Spacer(Modifier.height(100.dp))
            ExampleApp(rootComponent)
        }
    }
}