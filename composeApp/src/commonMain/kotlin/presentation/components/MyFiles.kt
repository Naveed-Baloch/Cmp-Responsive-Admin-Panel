package presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import models.MyFile
import models.Responsive
import models.getMyFiles
import org.jetbrains.compose.resources.painterResource
import utils.Colors

@Composable
fun MyFiles(responsive: Responsive, modifier: Modifier = Modifier) {
    val files = remember { getMyFiles() }
    LazyVerticalGrid(
        columns = GridCells.Fixed(if (responsive.isMobile) 2 else 4),
        modifier = modifier.height(if (responsive.isMobile) 320.dp else 160.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp, alignment = Alignment.CenterHorizontally),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        content = {
            items(files) {
                MyFileView(file = it)
            }
        }
    )
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
                Image(painter = painterResource(file.svg), contentDescription = "", modifier = Modifier.size(25.dp))
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




