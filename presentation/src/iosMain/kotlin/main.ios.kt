import androidx.compose.ui.window.ComposeUIViewController
import com.example.data.di.dataModule
import com.example.domain.di.domainModule
import com.example.features.gallery.GalleryScreen
import com.example.features.gallery.GalleryViewModel
import com.example.features.item.ItemScreen
import com.example.features.item.ItemViewModel
import org.koin.core.context.startKoin

object IoCContainer {
    fun startKoin() {
        startKoin {
            modules(dataModule + domainModule)
        }
    }
}

object ScreenProvider {
    fun createItemViewController() = ComposeUIViewController {
        ItemScreen(ItemViewModel())
    }

    fun createGalleryViewController() = ComposeUIViewController {
        GalleryScreen(GalleryViewModel())
    }
}