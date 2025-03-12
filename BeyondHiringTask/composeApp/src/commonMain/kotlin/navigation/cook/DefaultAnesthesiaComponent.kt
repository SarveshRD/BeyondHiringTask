package navigation.cook

import com.arkivanov.decompose.ComponentContext

class DefaultAnesthesiaComponent(
    componentContext: ComponentContext,
    private val onShowBackClicked: () -> Unit
) : AnesthesiaComponent,
    ComponentContext by componentContext {
    override fun onBackClicked() {
        onShowBackClicked()
    }
}
