package presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import presentation.components.MainContent
import presentation.components.SideMenu
import utils.Colors

@Composable
fun MainScreen() {
    var deviceWidth by remember { mutableStateOf(0.dp) }
    val isMobile by remember { derivedStateOf { deviceWidth < 600.dp } }
    val isTablet by remember { derivedStateOf { deviceWidth > 600.dp && deviceWidth < 1100.dp } }
    val isDesktop by remember { derivedStateOf { deviceWidth > 1100.dp } }

    val responsive = Responsive(isMobile = isMobile, isTablet = isTablet, isDesktop = isDesktop)
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(Colors.bgColor)
    ) {
        LaunchedEffect(this.maxWidth) {
            deviceWidth = this@BoxWithConstraints.maxWidth
        }
        Row {
            if (isDesktop) {
                SideMenu()
            }
            MainContent(modifier = Modifier.fillMaxSize(), responsive = responsive)
        }
    }
}


data class Responsive(
    val isMobile: Boolean,
    val isTablet: Boolean,
    val isDesktop: Boolean
)