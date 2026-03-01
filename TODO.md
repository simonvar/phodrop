# TODO

## 1. Sound in Video
- Add mute/unmute toggle button (speaker icon) on `SwipeCard` when displaying video
- Store mute state in `SwipeViewModel` (default: muted, as now)
- Pass `volume` to `VideoPlayer`, update `player.volume` reactively
- Show volume icon overlay on the card (e.g. bottom-right corner)

## 2. Fullscreen Media + Zoomable
- Add `FullscreenScreen` (new route `"fullscreen/{itemId}"`)
- Tap on `SwipeCard` → navigate to fullscreen
- For images: use `coil3` + `Modifier.pointerInput` for pinch-to-zoom and pan (or `me.saket.telephoto:zoomable-image-coil` library)
- For videos: fullscreen `VideoPlayer` with controls (`useController = true`), landscape support
- Back gesture / close button to return
- Update `GalleryNavigation` with the new route

## 3. Favorites
- Add `FavoritesManager` singleton (same pattern as `TrashManager` — `mutableStateListOf`)
- New swipe direction: swipe **up** → add to favorites
- Add star overlay icon on upward drag
- Add `FavoritesScreen` with `LazyVerticalGrid` (same layout as `TrashScreen`)
- Add favorites icon + badge in `SwipeScreen` TopAppBar (alongside trash)
- New route `"favorites"` in navigation

## 4. Undo Last Action
- Add `ActionHistory` — stack of `Action(type: TRASH | SKIP | FAVORITE, item: MediaItem)`
- Record each swipe in the history
- Show `Snackbar` after every swipe with "Undo" action
- On undo: pop from history, decrement `currentIndex`, remove from `TrashManager`/`FavoritesManager` if needed
- Use `SnackbarHostState` in `SwipeScreen`'s `Scaffold`
- Alternatively: a floating "undo" button (arrow-back icon) visible when history is non-empty
