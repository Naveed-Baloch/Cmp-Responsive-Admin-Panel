package presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cmp_responsive_admin_panel.composeapp.generated.resources.Documents
import cmp_responsive_admin_panel.composeapp.generated.resources.Res
import cmp_responsive_admin_panel.composeapp.generated.resources.drop_box
import cmp_responsive_admin_panel.composeapp.generated.resources.google_drive
import cmp_responsive_admin_panel.composeapp.generated.resources.one_drive
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import presentation.Responsive
import utils.Colors

data class MyFile(
    val title: String,
    val svg: DrawableResource,
    val totalStorage: String,
    val numOfFile: Int,
    val percentage: Float,
    val color: Color,
)

private fun getMyFiles(): List<MyFile> {
    val files = mutableListOf<MyFile>()
    files.add(MyFile(title = "Documents", svg = Res.drawable.Documents, totalStorage = "1.9GB", numOfFile = 1328, percentage = 35f, color = Colors.primaryColor))
    files.add(MyFile(title = "Google Drive", svg = Res.drawable.google_drive, totalStorage = "2.9GB", numOfFile = 1328, percentage = 35f, color = Color(0xFFFFA113)))
    files.add(MyFile(title = "One Drive", svg = Res.drawable.one_drive, totalStorage = "1GB", numOfFile = 1328, percentage = 10f, color = Color(0xFFA4CDFF)))
    files.add(MyFile(title = "Drop Box", svg = Res.drawable.drop_box, totalStorage = "7.9GB", numOfFile = 1328, percentage = 78f, color = Color(0xFF007EE5)))
    return files.toList()
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MyFiles(responsive: Responsive, modifier: Modifier = Modifier) {
    val files = remember { getMyFiles() }
    FlowRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalArrangement = Arrangement.spacedBy(20.dp),
        maxItemsInEachRow = if (responsive.isMobile) 2 else 4
    ) {
        files.forEach {
            MyFileView(file = it, modifier = Modifier)
        }
    }
}

@Composable
fun MyFileView(file: MyFile, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .width(170.dp)
            .height(150.dp)
            .background(Colors.secondaryColor)
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp, alignment = Alignment.CenterVertically),
        horizontalAlignment = Alignment.Start
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier.requiredSize(40.dp).clip(RoundedCornerShape(10.dp)).background(file.color.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                Image(painter = painterResource(file.svg), contentDescription = "")
            }
            Icon(Icons.Default.MoreVert, tint = Color.White, contentDescription = "")
        }
        Text(file.title, color = Color.White, fontSize = 16.sp)
        ProgressBar(file = file)
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text("${file.numOfFile} Files", color = Colors.white50, fontSize = 10.sp)
            Text(file.totalStorage, color = Color.White, fontSize = 12.sp)
        }

    }
}

@Composable
fun ProgressBar(file: MyFile, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Box(
            modifier = Modifier
                .height(7.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(file.color.copy(alpha = 0.1f))

        )
        Box(
            modifier = Modifier
                .height(7.dp)
                .fillMaxWidth(file.percentage / 100)
                .clip(RoundedCornerShape(10.dp))
                .background(file.color)

        )
    }
}




