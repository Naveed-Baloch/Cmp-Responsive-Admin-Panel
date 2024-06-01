package presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import cmp_responsive_admin_panel.composeapp.generated.resources.Documents
import cmp_responsive_admin_panel.composeapp.generated.resources.Res
import models.MenuItem
import models.Responsive
import models.getMenuItems
import org.jetbrains.compose.resources.painterResource
import utils.Colors
import utils.extensions.clickableWithoutRipple
import utils.extensions.disableClickAndRipple

enum class SideMenuState {
    Collapsed, Expanded
}

@Composable
fun SideMenu(responsive: Responsive, modifier: Modifier = Modifier, onItemClick: (MenuItem) -> Unit) {
    val menuItems = getMenuItems()
    var menuState by remember(responsive) { mutableStateOf(SideMenuState.Expanded) }
    val menuWidthAnimation by animateDpAsState(if(menuState == SideMenuState.Expanded) 250.dp else 60.dp , animationSpec = tween(durationMillis = 700))

    Column(
        modifier = modifier
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
            .widthIn(max = menuWidthAnimation)
            .background(Colors.secondaryColor)
            .disableClickAndRipple()
    ) {

        Box(modifier = Modifier.fillMaxWidth().requiredHeight(150.dp)) {
            Image(
                painter = painterResource(Res.drawable.Documents),
                modifier = Modifier.align(Alignment.Center).size(30.dp),
                contentDescription = "", contentScale = ContentScale.Inside
            )
        }
        Divider(color = Color.White.copy(alpha = 0.2f))
        Spacer(modifier = Modifier.height(10.dp))
        menuItems.forEach {
            MenuItemUi(menuItem = it, modifier = Modifier.clickable { onItemClick(it) }, menuState = menuState)
        }
        if (responsive.isDesktop) {
            Spacer(modifier = Modifier.Companion.weight(1f))
            MenuWidthToggleButton(
                menuState = menuState,
                onClick = {
                    menuState = if (menuState == SideMenuState.Expanded) {
                        SideMenuState.Collapsed
                    } else {
                        SideMenuState.Expanded
                    }
                }
            )
        }
    }
}
@Composable
private fun MenuWidthToggleButton(menuState: SideMenuState, onClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxWidth().padding(10.dp), contentAlignment = Alignment.BottomEnd) {
        Icon(
            if (menuState == SideMenuState.Expanded) Icons.Rounded.KeyboardArrowLeft else Icons.Rounded.KeyboardArrowRight,
            contentDescription = null, tint = Color.White,
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(Colors.primaryColor)
                .clickableWithoutRipple(onClick =  onClick)
                .padding(5.dp)
        )
    }
}

@Composable
fun MenuItemUi(menuItem: MenuItem, modifier: Modifier = Modifier, menuState: SideMenuState) {
    Row(
        modifier = modifier.height(40.dp).fillMaxWidth().animateContentSize(),
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(5.dp))
        Image(painter = painterResource(menuItem.resource), contentDescription = "", modifier = Modifier.requiredSize(15.dp))
        AnimatedVisibility(visible = menuState == SideMenuState.Expanded, enter = fadeIn(animationSpec = tween(700)), exit = fadeOut(animationSpec = tween(700))) {
            Text(menuItem.title, color = Color.White.copy(alpha = 0.5f), maxLines = 1)
        }
    }
}