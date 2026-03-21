package dev.simonvar.gallery.presentation.main

import android.net.Uri
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import dev.simonvar.gallery.data.MediaType
import dev.simonvar.gallery.presentation.home.HomeNode
import dev.simonvar.gallery.presentation.home.api.Home
import dev.simonvar.gallery.presentation.media.MediaNode
import dev.simonvar.gallery.presentation.media.api.Media
import dev.simonvar.gallery.presentation.trash.TrashNode
import dev.simonvar.gallery.presentation.trash.api.Trash
import androidx.core.net.toUri

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MainNode() {
    val navBackStack = rememberNavBackStack(Home)
    SharedTransitionLayout {
        NavDisplay(
            modifier = Modifier.fillMaxSize(),
            backStack = navBackStack,
            onBack = { navBackStack.removeLastOrNull() },
            entryDecorators = listOf(rememberSaveableStateHolderNavEntryDecorator()),
            sharedTransitionScope = this@SharedTransitionLayout,
            transitionSpec = {
                slideInHorizontally { it } togetherWith ExitTransition.KeepUntilTransitionsFinished
            },
            popTransitionSpec = {
                EnterTransition.None togetherWith slideOutHorizontally { it }
            },
            predictivePopTransitionSpec = {
                EnterTransition.None togetherWith slideOutHorizontally { it }
            },
            entryProvider = entryProvider {
                entry<Home> {
                    HomeNode(
                        modifier = Modifier.fillMaxSize(),
                        onNavigateToTrash = { navBackStack.add(Trash) },
                        onNavigateToMediaItem = { item ->
                            navBackStack.add(
                                Media(
                                    itemId = item.id,
                                    uriString = item.uri.toString(),
                                    mediaType = item.mediaType.name,
                                    displayName = item.displayName,
                                )
                            )
                        },
                        sharedTransitionScope = this@SharedTransitionLayout,
                    )
                }
                entry<Trash> {
                    TrashNode(
                        modifier = Modifier.fillMaxSize(),
                        onBack = { navBackStack.removeLastOrNull() }
                    )
                }
                entry<Media>(
                    metadata = NavDisplay.transitionSpec {
                        fadeIn() togetherWith ExitTransition.KeepUntilTransitionsFinished
                    } + NavDisplay.popTransitionSpec {
                        EnterTransition.None togetherWith fadeOut()
                    } + NavDisplay.predictivePopTransitionSpec {
                        EnterTransition.None togetherWith fadeOut()
                    }
                ) { mediaKey ->
                    MediaNode(
                        itemId = mediaKey.itemId,
                        uri = mediaKey.uriString.toUri(),
                        mediaType = MediaType.valueOf(mediaKey.mediaType),
                        displayName = mediaKey.displayName,
                        onBack = { navBackStack.removeLastOrNull() },
                        sharedTransitionScope = this@SharedTransitionLayout,
                    )
                }
            }
        )
    }
}
