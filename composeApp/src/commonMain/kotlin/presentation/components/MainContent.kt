package presentation.components

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import presentation.Responsive

@Composable
fun MainContent(modifier: Modifier = Modifier, responsive: Responsive, onMenuClick: () -> Unit) {
    Column(
        modifier = modifier
            .fillMaxSize().padding(15.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Header(modifier = Modifier.fillMaxWidth(), responsive = responsive, onMenuClick = onMenuClick)
        Details(responsive = responsive)
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Details(responsive: Responsive, modifier: Modifier = Modifier) {
    FlowRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalArrangement = Arrangement.spacedBy(20.dp),
        maxItemsInEachRow = if (responsive.isDesktop) 2 else 1,
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
            AddNewFilesSection(modifier = Modifier.fillMaxWidth(if (responsive.isDesktop) 0.67f else 1f))
            MyFiles(responsive = responsive, modifier = Modifier.fillMaxWidth(if (responsive.isDesktop) 0.67f else 1f))
            RecentFiles(modifier = Modifier.fillMaxWidth(if (responsive.isDesktop) 0.67f else 1f))

        }
        StorageDetails(modifier = Modifier.fillMaxWidth(if(responsive.isDesktop) 0.3f else 1f))
    }
}
