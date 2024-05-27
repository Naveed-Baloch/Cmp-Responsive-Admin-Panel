package presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import cmp_responsive_admin_panel.composeapp.generated.resources.Res
import cmp_responsive_admin_panel.composeapp.generated.resources.profile_pic
import cmp_responsive_admin_panel.composeapp.generated.resources.search
import org.jetbrains.compose.resources.painterResource
import presentation.Responsive
import utils.Colors
import utils.extensions.mapIf

@Composable
fun Header(modifier: Modifier = Modifier, responsive: Responsive, onMenuClick: () -> Unit) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
        if (responsive.isDesktop) {
            Text("Dashboard", color = Color.White)
        } else {
            Icon(Icons.Rounded.Menu,contentDescription = null, tint = Color.White, modifier = Modifier.clickable { onMenuClick() })
        }
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            SearchField(modifier = Modifier.height(55.dp).mapIf(responsive.isMobile) { fillMaxWidth(0.6f) }, responsive = responsive)
            ProfileSelection(modifier = Modifier.height(55.dp), responsive = responsive)
        }
    }
}

@Composable
fun ProfileSelection(modifier: Modifier = Modifier, responsive: Responsive) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(Colors.secondaryColor)
            .border(width = 1.dp, color = Color.White.copy(alpha = 0.1f), shape = RoundedCornerShape(10.dp))
            .padding(5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Image(painter = painterResource(Res.drawable.profile_pic), contentDescription = "", modifier = Modifier.size(25.dp))
        if (responsive.isDesktop) Text("Cmp Developer", color = Color.White, maxLines = 1, overflow = TextOverflow.Ellipsis)
        Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "", tint = Color.White, modifier = Modifier.size(30.dp))
    }

}

@Composable
private fun SearchField(modifier: Modifier = Modifier, responsive: Responsive) {
    var query by remember { mutableStateOf("") }
    TextField(
        value = query,
        onValueChange = { query = it },
        modifier = modifier.clip(RoundedCornerShape(10.dp)).background(Colors.secondaryColor),
        trailingIcon = {
            Box(
                modifier = Modifier.requiredSize(30.dp).clip(RoundedCornerShape(10.dp)).background(Colors.primaryColor),
                contentAlignment = Alignment.Center
            ) {
                Image(painter = painterResource(Res.drawable.search), contentDescription = "", modifier = Modifier.size(10.dp))
            }
        },
        placeholder = { Text("Search", color = Color.White) },
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Colors.white50,
            backgroundColor = Colors.secondaryColor,
            cursorColor = Colors.white50,
            focusedBorderColor = Colors.secondaryColor,
            unfocusedBorderColor = Colors.secondaryColor,
            disabledBorderColor = Colors.secondaryColor,
        ),
    )
}
