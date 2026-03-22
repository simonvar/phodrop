package dev.simonvar.gallery.presentation.home

import dev.simonvar.gallery.data.MediaItem

enum class ActionType { TRASH, SKIP }

data class HistoryEntry(val type: ActionType, val item: MediaItem)
