package org.example.beyondhiringtask

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import navigation.root.RootComponent
import org.example.beyondhiringtask.di.appModule
import org.koin.compose.KoinApplication
import presentation.screens.AnesthesiaDetailScreen

@Composable
@Preview
fun App(
    rootComponent: RootComponent,
    modifier: Modifier = Modifier,
) {

    val routerState by rootComponent.routerState.subscribeAsState()


    KoinApplication(application = { modules(appModule()) }) {
        MaterialTheme {
            Scaffold() {
                Surface(
                    modifier = modifier.fillMaxSize().windowInsetsPadding(WindowInsets.systemBars)
                ) {
                    when (val child = routerState.active.instance) {
                        is RootComponent.RootChild.Anesthesia -> {
                            AnesthesiaDetailScreen(
                                child.component
                            )
                        }

                    }
                }
            }
        }
    }
}