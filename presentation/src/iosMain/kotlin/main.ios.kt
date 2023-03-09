import androidx.compose.ui.window.ComposeUIViewController
import com.example.data.di.dataModule
import com.example.domain.di.domainModule
import com.example.features.gallery.GalleryScreen
import com.example.features.item.ItemScreen
import org.koin.core.context.startKoin

object IoCContainer {
    fun startKoin() {
        startKoin {
            modules(dataModule + domainModule)
        }
    }
}

object ScreenDecorator {
    fun createItemViewController() = ComposeUIViewController {
        ItemScreen()
    }

    fun createGalleryViewController() = ComposeUIViewController {
        GalleryScreen()
    }
}