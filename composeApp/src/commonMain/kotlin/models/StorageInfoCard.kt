package models

import cmp_responsive_admin_panel.composeapp.generated.resources.Documents
import cmp_responsive_admin_panel.composeapp.generated.resources.Res
import cmp_responsive_admin_panel.composeapp.generated.resources.folder
import cmp_responsive_admin_panel.composeapp.generated.resources.media
import cmp_responsive_admin_panel.composeapp.generated.resources.unknown
import org.jetbrains.compose.resources.DrawableResource

data class StorageInfoCard(
    val svg: DrawableResource,
    val title: String,
    val amountOfFiles: String,
    val numOfFiles: Int,
)

fun getStorageInfoCards() : List<StorageInfoCard> {
    val cards = mutableListOf<StorageInfoCard>()
    cards.add(StorageInfoCard(svg = Res.drawable.Documents, title = "Documents Files", amountOfFiles = "1.3GB", numOfFiles = 1328))
    cards.add(StorageInfoCard(svg = Res.drawable.media, title = "Documents Files", amountOfFiles = "1.3GB", numOfFiles = 1000))
    cards.add(StorageInfoCard(svg = Res.drawable.folder, title = "Media Files", amountOfFiles = "1.3GB", numOfFiles = 500))
    cards.add(StorageInfoCard(svg = Res.drawable.unknown, title = "Other Files", amountOfFiles = "1.3GB", numOfFiles = 140))
    cards.add(StorageInfoCard(svg = Res.drawable.folder, title = "Media Files", amountOfFiles = "1.3GB", numOfFiles = 500))
    cards.add(StorageInfoCard(svg = Res.drawable.unknown, title = "Other Files", amountOfFiles = "1.3GB", numOfFiles = 140))
    return cards
}