package presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.snap
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import models.Responsive
import presentation.components.MainContent
import presentation.components.SideMenu
import utils.Colors
import utils.extensions.clickableWithoutRipple
import utils.extensions.mapIf

@Composable
fun MainScreen() {
    var deviceWidth by remember { mutableStateOf(0.dp) }
    val isMobile by remember { derivedStateOf { deviceWidth < 600.dp } }
    val isTablet by remember { derivedStateOf { deviceWidth > 600.dp && deviceWidth < 1100.dp } }
    val isDesktop by remember { derivedStateOf { deviceWidth > 1100.dp } }
    var showDrawer by remember { mutableStateOf(false) }
    val mainContentAlpha by animateFloatAsState(if (showDrawer) 0.6f else 1f)

    LaunchedEffect(key1 = isDesktop) {
        if (isDesktop) {
            showDrawer = false
        }
    }

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
                SideMenu(onItemClick = { showDrawer = false })
            }
            MainContent(
                modifier = Modifier.fillMaxSize().alpha(mainContentAlpha).clickableWithoutRipple { showDrawer = false },
                responsive = responsive,
                onMenuClick = { showDrawer = !showDrawer }
            )
        }
        AnimatedVisibility(
            visible = showDrawer,
            enter = slideInHorizontally(animationSpec = tween()) { -it },
            exit = slideOutHorizontally(animationSpec = if (isDesktop) snap() else spring()) { -it }
        ) {
            SideMenu(modifier = Modifier.mapIf(isMobile) { width(220.dp) }, onItemClick = { showDrawer = false })
        }
    }
}