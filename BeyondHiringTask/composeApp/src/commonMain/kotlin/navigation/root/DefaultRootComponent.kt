package navigation.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.replaceCurrent
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import navigation.cook.AnesthesiaComponent
import navigation.cook.DefaultAnesthesiaComponent
import navigation.root.DefaultRootComponent.RootConfig.*
import navigation.root.RootComponent.RootChild

class DefaultRootComponent(
    componentContext: ComponentContext,
) : RootComponent,
    ComponentContext by componentContext {

    private val rootNavigation = StackNavigation<RootConfig>()

    override val routerState: Value<ChildStack<*, RootChild>> =
        childStack(
            source = rootNavigation,
            serializer = RootConfig.serializer(),
            initialStack = { listOf(RootConfig.Anesthesia) },
            handleBackButton = true,
            childFactory = ::child,
        )

    override fun navigateToAnesthesia() {
        rootNavigation.replaceCurrent(Anesthesia)
    }


    private fun child(config: RootConfig, componentContext: ComponentContext): RootChild =
        when (config) {
            is Anesthesia -> RootChild.Anesthesia(
                anesthesiaComponent(
                    componentContext
                )
            )
        }


    @Serializable
    private sealed interface RootConfig {
        @Serializable
        data object Anesthesia : RootConfig


    }

    private fun anesthesiaComponent(componentContext: ComponentContext): AnesthesiaComponent =
        DefaultAnesthesiaComponent(componentContext = componentContext,
            onShowBackClicked = { rootNavigation.pop() })


}