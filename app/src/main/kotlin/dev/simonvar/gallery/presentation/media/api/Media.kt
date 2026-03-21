package dev.simonvar.gallery.presentation.media.api

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data class Media(
    val itemId: Long,
    val uriString: String,
    val mediaType: String,
    val displayName: String,
) : NavKey
