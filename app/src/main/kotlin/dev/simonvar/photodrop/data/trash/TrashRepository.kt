package dev.simonvar.photodrop.data.trash

import dev.simonvar.photodrop.data.MediaItem

interface TrashRepository {

    fun add(item: MediaItem)

    fun remove(item: MediaItem)

    fun clear()
}