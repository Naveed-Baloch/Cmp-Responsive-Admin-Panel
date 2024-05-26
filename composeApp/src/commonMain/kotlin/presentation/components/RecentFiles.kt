package presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cmp_responsive_admin_panel.composeapp.generated.resources.Figma_file
import cmp_responsive_admin_panel.composeapp.generated.resources.Res
import cmp_responsive_admin_panel.composeapp.generated.resources.doc_file
import cmp_responsive_admin_panel.composeapp.generated.resources.excel_file
import cmp_responsive_admin_panel.composeapp.generated.resources.media_file
import cmp_responsive_admin_panel.composeapp.generated.resources.pdf_file
import cmp_responsive_admin_panel.composeapp.generated.resources.sound_file
import cmp_responsive_admin_panel.composeapp.generated.resources.xd_file
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import utils.Colors

data class RecentFile(
    val iconSvg: DrawableResource,
    val title: String,
    val date: String,
    val size: String
)

private fun getRecentFiles(): List<RecentFile> {
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
@Composable
fun RecentFiles(modifier: Modifier = Modifier) {
    val files = remember { getRecentFiles() }
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(Colors.secondaryColor)
            .padding(20.dp)
    ) {
        Text("Recent Files", color = Color.White, fontSize = 16.sp)
        Spacer(modifier = Modifier.height(20.dp))
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween){
            Text("File Name", color = Color.White)
            Text("Date", color = Color.White)
            Text("Size", color = Color.White)
        }
        Spacer(modifier = Modifier.height(10.dp))
        Divider(color = Color.White.copy(alpha = 0.2f))
        files.forEach {
            Spacer(modifier = Modifier.height(10.dp))
            RecentFileUi(recentFile = it)
            Spacer(modifier = Modifier.height(10.dp))
            Divider(color = Color.White.copy(alpha = 0.2f))

        }
    }
}

@Composable
fun RecentFileUi(recentFile: RecentFile) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Row(modifier = Modifier, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            Image(painter = painterResource(recentFile.iconSvg), contentDescription = "")
            Text(recentFile.title, color = Color.White)
        }
        Text(recentFile.date, color = Color.White)
        Text(recentFile.size, color = Color.White)
    }
}