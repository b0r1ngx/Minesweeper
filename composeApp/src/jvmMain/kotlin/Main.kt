import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.lifecycle.LifecycleController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.minesweeper.App
import com.arkivanov.minesweeper.game.DefaultGameComponent
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory

fun main() {
    val lifecycle = LifecycleRegistry()

    val root =
        DefaultGameComponent(
            componentContext = DefaultComponentContext(lifecycle = lifecycle),
            storeFactory = DefaultStoreFactory(),
        )

    application {
        val windowState = rememberWindowState()

        Window(onCloseRequest = ::exitApplication, title = "Minesweeper", state = windowState) {
            App(component = root)
        }

        @OptIn(ExperimentalDecomposeApi::class)
        LifecycleController(lifecycle, windowState)
    }
}
