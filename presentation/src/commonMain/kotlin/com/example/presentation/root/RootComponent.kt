package com.example.presentation.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.example.presentation.gallery.GalleryComponent
import com.example.presentation.gallery.GalleryComponentImpl
import com.example.presentation.item.ItemComponent
import com.example.presentation.item.ItemComponentImpl

interface RootComponent {

    val childStack: Value<ChildStack<*, Child>>

    fun navigateToGallery()
    fun navigateToItem()
    fun navigateToEmpty()

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

    override fun navigateToGallery() {
        navigation.push(Configuration.Gallery)
    }

    override fun navigateToItem() {
        navigation.push(Configuration.Item)
    }

    override fun navigateToEmpty() {
        navigation.push(Configuration.Empty)
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