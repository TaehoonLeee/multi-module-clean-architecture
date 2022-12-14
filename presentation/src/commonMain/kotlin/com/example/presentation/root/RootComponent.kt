package com.example.presentation.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.*
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.example.features.gallery.GalleryComponent
import com.example.features.gallery.GalleryComponentImpl
import com.example.features.item.ItemComponent
import com.example.features.item.ItemComponentImpl

interface RootComponent {

    val childStack: Value<ChildStack<*, Child>>

    fun navigate(route: String)

    sealed class Child {
        class Gallery(val component: GalleryComponent) : Child()
        class Item(val component: ItemComponent) : Child()
        object Empty : Child()
    }
}

class RootComponentImpl(
    context: ComponentContext
) : RootComponent, ComponentContext by context {

    private val navigation = StackNavigation<Configuration>()

    override val childStack: Value<ChildStack<*, RootComponent.Child>> = childStack(
        source = navigation,
        initialConfiguration = Configuration.Gallery,
        handleBackButton = true,
        childFactory = ::resolveChild
    )

    override fun navigate(route: String) {
        val destination = when (route) {
            "item" -> Configuration.Item
            "gallery" -> Configuration.Gallery
            "empty" -> Configuration.Empty
            else -> throw IllegalStateException()
        }

        navigation.replaceCurrent(destination)
    }

    private fun resolveChild(configuration: Configuration, componentContext: ComponentContext): RootComponent.Child {
        return when (configuration) {
            is Configuration.Item -> RootComponent.Child.Item(ItemComponentImpl(componentContext))
            is Configuration.Gallery -> RootComponent.Child.Gallery(GalleryComponentImpl(componentContext))
            is Configuration.Empty -> RootComponent.Child.Empty
        }
    }

    private sealed interface Configuration : Parcelable {
        @Parcelize
        object Item : Configuration

        @Parcelize
        object Gallery : Configuration

        @Parcelize
        object Empty : Configuration
    }
}