package models

import cmp_responsive_admin_panel.composeapp.generated.resources.Figma_file
import cmp_responsive_admin_panel.composeapp.generated.resources.Res
import cmp_responsive_admin_panel.composeapp.generated.resources.doc_file
import cmp_responsive_admin_panel.composeapp.generated.resources.excel_file
import cmp_responsive_admin_panel.composeapp.generated.resources.media_file
import cmp_responsive_admin_panel.composeapp.generated.resources.pdf_file
import cmp_responsive_admin_panel.composeapp.generated.resources.sound_file
import cmp_responsive_admin_panel.composeapp.generated.resources.xd_file
import org.jetbrains.compose.resources.DrawableResource

data class RecentFile(
    val iconSvg: DrawableResource,
    val title: String,
    val date: String,
    val size: String
)

fun getRecentFiles(): List<RecentFile> {
    val files = mutableListOf<RecentFile>()
    files.add(RecentFile(iconSvg = Res.drawable.xd_file, title = "XD File", date = "01-03-2021", size = "3.5mb"))
    files.add(RecentFile(iconSvg = Res.drawable.Figma_file, title = "Figma File", date = "04-03-2021", size = "13.5mb"))
    files.add(RecentFile(iconSvg = Res.drawable.doc_file, title = "Document", date = "05-03-2021", size = "31.5mb"))
    files.add(RecentFile(iconSvg = Res.drawable.sound_file, title = "Sound File", date = "06-03-2021", size = "35.5mb"))
    files.add(RecentFile(iconSvg = Res.drawable.media_file, title = "Media File", date = "07-03-2021", size = "3.5mb"))
    files.add(RecentFile(iconSvg = Res.drawable.pdf_file, title = "Pdf File", date = "08-03-2021", size = "34.5mb"))
    files.add(RecentFile(iconSvg = Res.drawable.excel_file, title = "Excel File", date = "09-03-2021", size = "34.5mb"))
    return files.toList()
}