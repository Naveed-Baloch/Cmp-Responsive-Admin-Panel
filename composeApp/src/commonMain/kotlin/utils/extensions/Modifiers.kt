package utils.extensions

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

inline fun Modifier.mapIf(predicate: Boolean, block: Modifier.() -> Modifier): Modifier {
    return if (predicate) block() else this
}

@Composable
fun Modifier.disableClickAndRipple(): Modifier {
    return this then Modifier.clickable(enabled = false, indication = null, interactionSource = remember { MutableInteractionSource() }, onClick = {})
}

@Composable
fun Modifier.clickableWithoutRipple(enabled: Boolean = true, onClick: () -> Unit): Modifier {
    return this then Modifier.clickable(enabled = enabled, indication = null, interactionSource = remember { MutableInteractionSource() }, onClick = onClick)
}