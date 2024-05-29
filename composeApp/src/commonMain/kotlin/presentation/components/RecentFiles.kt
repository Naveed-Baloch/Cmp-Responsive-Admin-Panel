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
import models.RecentFile
import models.getRecentFiles
import org.jetbrains.compose.resources.painterResource
import utils.Colors

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
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
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
    ) {
        Row(modifier = Modifier, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            Image(painter = painterResource(recentFile.iconSvg), contentDescription = "")
            Text(recentFile.title, color = Color.White, maxLines = 1)
        }
        Text(recentFile.date, color = Color.White, maxLines = 1)
        Text(recentFile.size, color = Color.White, maxLines = 1)
    }
}