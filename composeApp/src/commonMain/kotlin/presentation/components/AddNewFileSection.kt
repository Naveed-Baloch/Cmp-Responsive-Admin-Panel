package presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import utils.Colors

@Composable
fun AddNewFilesSection(modifier: Modifier = Modifier) {
    Row(
        modifier, horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically
    ) {
        Text("My Files", color = Color.White)
        Box(modifier = Modifier.clip(RoundedCornerShape(5.dp)).background(color = Colors.primaryColor).padding(10.dp)) {
            Row(modifier = Modifier, horizontalArrangement = Arrangement.spacedBy(5.dp), verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.Add, tint = Color.White, contentDescription = "Add Files Buttons")
                Text("Add New", color = Color.White)
            }
        }
    }
}
