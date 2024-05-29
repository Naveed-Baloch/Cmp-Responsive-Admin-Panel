package models

import cmp_responsive_admin_panel.composeapp.generated.resources.Res
import cmp_responsive_admin_panel.composeapp.generated.resources.menu_dashboard
import cmp_responsive_admin_panel.composeapp.generated.resources.menu_doc
import cmp_responsive_admin_panel.composeapp.generated.resources.menu_notification
import cmp_responsive_admin_panel.composeapp.generated.resources.menu_profile
import cmp_responsive_admin_panel.composeapp.generated.resources.menu_setting
import cmp_responsive_admin_panel.composeapp.generated.resources.menu_store
import cmp_responsive_admin_panel.composeapp.generated.resources.menu_task
import cmp_responsive_admin_panel.composeapp.generated.resources.menu_tran
import org.jetbrains.compose.resources.DrawableResource

data class MenuItem(
    val resource: DrawableResource,
    val title: String,
    val onClick: () -> Unit,
)

fun getMenuItems(): List<MenuItem> {
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
