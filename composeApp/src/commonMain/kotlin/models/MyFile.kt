package models

import androidx.compose.ui.graphics.Color
import cmp_responsive_admin_panel.composeapp.generated.resources.Documents
import cmp_responsive_admin_panel.composeapp.generated.resources.Res
import cmp_responsive_admin_panel.composeapp.generated.resources.drop_box
import cmp_responsive_admin_panel.composeapp.generated.resources.google_drive
import cmp_responsive_admin_panel.composeapp.generated.resources.one_drive
import org.jetbrains.compose.resources.DrawableResource
import utils.Colors

data class MyFile(
    val title: String,
    val svg: DrawableResource,
    val totalStorage: String,
    val numOfFile: Int,
    val percentage: Float,
    val color: Color,
)

fun getMyFiles(): List<MyFile> {
    val files = mutableListOf<MyFile>()
    files.add(MyFile(title = "Documents", svg = Res.drawable.Documents, totalStorage = "1.9GB", numOfFile = 1328, percentage = 35f, color = Colors.primaryColor))
    files.add(MyFile(title = "Google Drive", svg = Res.drawable.google_drive, totalStorage = "2.9GB", numOfFile = 1328, percentage = 35f, color = Color(0xFFFFA113)))
    files.add(MyFile(title = "One Drive", svg = Res.drawable.one_drive, totalStorage = "1GB", numOfFile = 1328, percentage = 10f, color = Color(0xFFA4CDFF)))
    files.add(MyFile(title = "Drop Box", svg = Res.drawable.drop_box, totalStorage = "7.9GB", numOfFile = 1328, percentage = 78f, color = Color(0xFF007EE5)))
    return files.toList()
}
