package presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cmp_responsive_admin_panel.composeapp.generated.resources.Documents
import cmp_responsive_admin_panel.composeapp.generated.resources.Res
import cmp_responsive_admin_panel.composeapp.generated.resources.folder
import cmp_responsive_admin_panel.composeapp.generated.resources.media
import cmp_responsive_admin_panel.composeapp.generated.resources.unknown
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import utils.Colors
import utils.PieChart
import utils.PieChartSliceData

private fun getPieChartSlices() : List<PieChartSliceData> {
    val slices = mutableListOf<PieChartSliceData>()
    slices.add(PieChartSliceData(radius = 25.dp, value = 25, color = Colors.primaryColor))
    slices.add(PieChartSliceData(radius = 22.dp, value = 20, color = Color(0xFF26E5FF)))
    slices.add(PieChartSliceData(radius = 16.dp, value = 15, color = Color(0xFFFFCF26)))
    slices.add(PieChartSliceData(radius = 13.dp, value = 19, color = Color(0xFFEE2727)))
    slices.add(PieChartSliceData(radius = 10.dp, value = 25, color = Colors.primaryColor.copy(alpha = 0.1f)))
    return slices
}

data class StorageInfoCard(
    val svg: DrawableResource,
    val title: String,
    val amountOfFiles: String,
    val numOfFiles: Int,
)

private fun getStorageInfoCards() : List<StorageInfoCard> {
    val cards = mutableListOf<StorageInfoCard>()
    cards.add(StorageInfoCard(svg = Res.drawable.Documents, title = "Documents Files", amountOfFiles = "1.3GB", numOfFiles = 1328))
    cards.add(StorageInfoCard(svg = Res.drawable.media, title = "Documents Files", amountOfFiles = "1.3GB", numOfFiles = 1000))
    cards.add(StorageInfoCard(svg = Res.drawable.folder, title = "Media Files", amountOfFiles = "1.3GB", numOfFiles = 500))
    cards.add(StorageInfoCard(svg = Res.drawable.unknown, title = "Other Files", amountOfFiles = "1.3GB", numOfFiles = 140))
    return cards
}

@Composable
fun StorageDetails(modifier: Modifier = Modifier) {
    val pieChartSlices = remember { getPieChartSlices() }
    val storageCards = remember { getStorageInfoCards() }
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(Colors.secondaryColor)
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Storage Details", color = Color.White)
        PieChart(sliceData = pieChartSlices)
        storageCards.forEach { StorageCard(it) }
    }
}

@Composable
private fun StorageCard(card: StorageInfoCard, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .border(width = 1.dp, color = Colors.primaryColor.copy(alpha = 0.3f), shape = RoundedCornerShape(10.dp))
            .padding(vertical = 10.dp, horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(painterResource(card.svg), contentDescription = "", modifier = Modifier.size(25.dp))
            Column(modifier = Modifier.padding(start = 10.dp).fillMaxWidth(0.7f)) {
                Text(card.title, color = Color.White, fontSize = 14.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Text("${card.numOfFiles} Files", color = Colors.white50, fontSize = 12.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
            }
        }
        Text(card.amountOfFiles, color = Color.White, fontSize = 14.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
    }

}