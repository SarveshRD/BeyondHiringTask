package navigation.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import navigation.cook.AnesthesiaComponent
interface RootComponent {

    val routerState: Value<ChildStack<*, RootChild>>

    fun navigateToAnesthesia()

    sealed class RootChild {
        data class Anesthesia(val component: AnesthesiaComponent) : RootChild()
    }
}