package dev.simonvar.gallery.presentation.media

import android.net.Uri
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.navigation3.ui.LocalNavAnimatedContentScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.simonvar.gallery.data.MediaType
import me.saket.telephoto.zoomable.coil3.ZoomableAsyncImage

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MediaNode(
    itemId: Long,
    uri: Uri,
    mediaType: MediaType,
    displayName: String,
    onBack: () -> Unit,
    sharedTransitionScope: SharedTransitionScope,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .systemBarsPadding(),
        contentAlignment = Alignment.Center,
    ) {
        if (mediaType == MediaType.VIDEO) {
            FullscreenVideoPlayer(
                uri = uri,
                modifier = Modifier.fillMaxSize(),
            )
        } else {
            with(sharedTransitionScope) {
                ZoomableAsyncImage(
                    model = uri,
                    contentDescription = displayName,
                    modifier = Modifier
                        .sharedElement(
                            rememberSharedContentState(key = "media_image_${itemId}"),
                            animatedVisibilityScope = LocalNavAnimatedContentScope.current,
                        )
                        .fillMaxSize(),
                )
            }
        }

        // Close button
        IconButton(
            onClick = onBack,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
                .background(
                    color = Color.Black.copy(alpha = 0.5f),
                    shape = CircleShape,
                )
                .size(40.dp),
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Close",
                tint = Color.White,
            )
        }
    }
}
