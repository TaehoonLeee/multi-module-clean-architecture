import androidx.compose.ui.window.Application
import com.example.presentation.root.ExampleApp
import com.example.presentation.root.RootComponent
import platform.UIKit.UIViewController
import platform.UIKit.UIWindow

fun createExampleViewController(
    window: UIWindow,
    rootComponent: RootComponent
): UIViewController = Application("Example") {
    ExampleApp(rootComponent)
}