package utils.extensions

import androidx.compose.ui.Modifier

inline fun Modifier.mapIf(predicate: Boolean, block: Modifier.() -> Modifier): Modifier {
    return if (predicate) block() else this
}