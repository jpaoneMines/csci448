package edu.mines.csci448.ui.compose

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.offset
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity

private enum class SwipeDirection {
    Left,
    Initial,
    Right
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwipeableSnackbarHost(hostState: SnackbarHostState) {
    if (hostState.currentSnackbarData == null) { return }

    val sizeState = remember { mutableStateOf(Size.Zero) }
    val swipeableState = rememberSwipeableState(SwipeDirection.Initial)
    val width = remember(sizeState.value) {
        if (sizeState.value.width == 0f) {
            1f
        } else {
            sizeState.value.width
        }
    }

    if (swipeableState.isAnimationRunning) {
        DisposableEffect(Unit) {
            onDispose {
                when (swipeableState.currentValue) {
                    SwipeDirection.Right,
                    SwipeDirection.Left -> {
                        hostState.currentSnackbarData?.dismiss()
                    }
                    else -> {
                        return@onDispose
                    }
                }
            }
        }
    }

    val offset = with(LocalDensity.current) {
        swipeableState.offset.value.toDp()
    }

    SnackbarHost(
        modifier = Modifier
            .onSizeChanged { sizeState.value = Size(it.width.toFloat(), it.height.toFloat()) }
            .swipeable(
                state = swipeableState,
                anchors = mapOf(
                    -width to SwipeDirection.Left,
                    0f to SwipeDirection.Initial,
                    width to SwipeDirection.Right
                ),
                thresholds = { _, _ -> FractionalThreshold(0.3f) },
                orientation = Orientation.Horizontal
            ),
        hostState = hostState,
        snackbar = { snackbarData ->
            Snackbar(
                modifier = Modifier.offset(x = offset),
                snackbarData = snackbarData
            )
        }
    )
}