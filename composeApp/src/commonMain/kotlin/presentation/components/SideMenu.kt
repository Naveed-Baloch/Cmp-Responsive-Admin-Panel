package presentation.components

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
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import cmp_responsive_admin_panel.composeapp.generated.resources.Res
import cmp_responsive_admin_panel.composeapp.generated.resources.logo
import cmp_responsive_admin_panel.composeapp.generated.resources.menu_dashboard
import cmp_responsive_admin_panel.composeapp.generated.resources.menu_doc
import cmp_responsive_admin_panel.composeapp.generated.resources.menu_notification
import cmp_responsive_admin_panel.composeapp.generated.resources.menu_profile
import cmp_responsive_admin_panel.composeapp.generated.resources.menu_setting
import cmp_responsive_admin_panel.composeapp.generated.resources.menu_store
import cmp_responsive_admin_panel.composeapp.generated.resources.menu_task
import cmp_responsive_admin_panel.composeapp.generated.resources.menu_tran
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import utils.Colors
import utils.extensions.disableClickAndRipple

data class MenuItem(
    val resource: DrawableResource,
    val title: String,
    val onClick: () -> Unit,
)


private fun getMenuItems(): List<MenuItem> {
    val items = mutableListOf<MenuItem>()
    items.add(MenuItem(resource = Res.drawable.menu_dashboard , title = "Dashboard", onClick = {}))
    items.add(MenuItem(resource = Res.drawable.menu_tran , title = "Transaction", onClick = {}))
    items.add(MenuItem(resource = Res.drawable.menu_task , title = "Task", onClick = {}))
    items.add(MenuItem(resource = Res.drawable.menu_doc , title = "Documents", onClick = {}))
    items.add(MenuItem(resource = Res.drawable.menu_store , title = "Store", onClick = {}))
    items.add(MenuItem(resource = Res.drawable.menu_notification , title = "Notification", onClick = {}))
    items.add(MenuItem(resource = Res.drawable.menu_profile , title = "Profile", onClick = {}))
    items.add(MenuItem(resource = Res.drawable.menu_setting , title = "Settings", onClick = {}))
    return items.toList()
}

@Composable
fun SideMenu(modifier: Modifier = Modifier,onItemClick:(MenuItem) -> Unit) {
    val menuItems = getMenuItems()
    Column(
        modifier = modifier
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
            .widthIn(max = 250.dp)
            .background(Colors.secondaryColor)
            .disableClickAndRipple()
    ) {

        Box(modifier = Modifier.fillMaxWidth().requiredHeight(150.dp)) {
            Image(
                painter = painterResource(Res.drawable.logo),
                modifier = Modifier.align(Alignment.Center).size(100.dp),
                contentDescription = "", contentScale = ContentScale.Inside
            )
        }
        Divider(color = Color.White.copy(alpha = 0.2f))
        Spacer(modifier = Modifier.height(10.dp))
        menuItems.forEach {
            MenuItemUi(menuItem = it, modifier = Modifier.clickable { onItemClick(it) })
        }
    }
}

@Composable
fun MenuItemUi(menuItem: MenuItem, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.height(40.dp).fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(5.dp))
        Image(painter = painterResource(menuItem.resource), contentDescription = "", modifier = Modifier.requiredSize(15.dp))
        Text(menuItem.title, color = Color.White.copy(alpha = 0.5f))
    }
}