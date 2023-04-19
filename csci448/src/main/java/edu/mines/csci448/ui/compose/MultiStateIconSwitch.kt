package edu.mines.csci448.ui.compose

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun MultiStateIconSwitch(
    icons: Array<ImageVector>,
    contentDescriptions: Array<String>,
    selectedIndex: Int,
    orientation: MultiStateSwitchOrientation = MultiStateSwitchOrientation.HORIZONTAL,
    backgroundColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    outlineColor: Color = MaterialTheme.colorScheme.outline,
    selectedIconColor: Color = MaterialTheme.colorScheme.onPrimary,
    selectedBackgroundColor: Color = MaterialTheme.colorScheme.primary,
    unselectedIconColor: Color = MaterialTheme.colorScheme.outline,
    unselectedBackgroundColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    roundedCornerShapeSize: Dp = 24.dp,
    borderWidth: Dp = 2.dp,
    innerPaddingVertical: Dp = 16.dp,
    innerPaddingHorizontal: Dp = 16.dp,
    onSelect: (selectedIndex: Int) -> Unit
) {
    val iconContent: MutableList<@Composable () -> Unit> = mutableListOf()
    icons.forEachIndexed { index, iconVector ->
        val contentDescription = if(index < contentDescriptions.size) { contentDescriptions[index] } else { null }
        iconContent += {
            Icon(
                imageVector = iconVector,
                contentDescription = contentDescription,
                tint = if(index == selectedIndex) { selectedIconColor } else { unselectedIconColor }
            )
        }
    }
    MultiStateSwitch(
        options = iconContent.toTypedArray(),
        selectedIndex = selectedIndex,
        orientation = orientation,
        backgroundColor = backgroundColor,
        outlineColor = outlineColor,
        selectedBackgroundColor = selectedBackgroundColor,
        unselectedBackgroundColor = unselectedBackgroundColor,
        roundedCornerShapeSize = roundedCornerShapeSize,
        borderWidth = borderWidth,
        innerPaddingVertical = innerPaddingVertical,
        innerPaddingHorizontal = innerPaddingHorizontal,
        onSelect = onSelect
    )
}

@Composable
fun MultiStateIconSwitchRow(
    icons: Array<ImageVector>,
    contentDescriptions: Array<String>,
    selectedIndex: Int,
    backgroundColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    outlineColor: Color = MaterialTheme.colorScheme.outline,
    selectedIconColor: Color = MaterialTheme.colorScheme.onPrimary,
    selectedBackgroundColor: Color = MaterialTheme.colorScheme.primary,
    unselectedIconColor: Color = MaterialTheme.colorScheme.outline,
    unselectedBackgroundColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    roundedCornerShapeSize: Dp = 24.dp,
    borderWidth: Dp = 2.dp,
    innerPaddingVertical: Dp = 16.dp,
    innerPaddingHorizontal: Dp = 16.dp,
    onSelect: (selectedIndex: Int) -> Unit
) {
    MultiStateIconSwitch(
        icons = icons,
        contentDescriptions = contentDescriptions,
        selectedIndex = selectedIndex,
        orientation = MultiStateSwitchOrientation.HORIZONTAL,
        backgroundColor = backgroundColor,
        outlineColor = outlineColor,
        selectedIconColor = selectedIconColor,
        selectedBackgroundColor = selectedBackgroundColor,
        unselectedIconColor = unselectedIconColor,
        unselectedBackgroundColor = unselectedBackgroundColor,
        roundedCornerShapeSize = roundedCornerShapeSize,
        borderWidth = borderWidth,
        innerPaddingVertical = innerPaddingVertical,
        innerPaddingHorizontal = innerPaddingHorizontal,
        onSelect = onSelect
    )
}

@Composable
fun MultiStateIconSwitchColumn(
    icons: Array<ImageVector>,
    contentDescriptions: Array<String>,
    selectedIndex: Int,
    backgroundColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    outlineColor: Color = MaterialTheme.colorScheme.outline,
    selectedIconColor: Color = MaterialTheme.colorScheme.onPrimary,
    selectedBackgroundColor: Color = MaterialTheme.colorScheme.primary,
    unselectedIconColor: Color = MaterialTheme.colorScheme.outline,
    unselectedBackgroundColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    roundedCornerShapeSize: Dp = 24.dp,
    borderWidth: Dp = 2.dp,
    innerPaddingVertical: Dp = 16.dp,
    innerPaddingHorizontal: Dp = 16.dp,
    onSelect: (selectedIndex: Int) -> Unit
) {
    MultiStateIconSwitch(
        icons = icons,
        contentDescriptions = contentDescriptions,
        selectedIndex = selectedIndex,
        orientation = MultiStateSwitchOrientation.VERTICAL,
        backgroundColor = backgroundColor,
        outlineColor = outlineColor,
        selectedIconColor = selectedIconColor,
        selectedBackgroundColor = selectedBackgroundColor,
        unselectedIconColor = unselectedIconColor,
        unselectedBackgroundColor = unselectedBackgroundColor,
        roundedCornerShapeSize = roundedCornerShapeSize,
        borderWidth = borderWidth,
        innerPaddingVertical = innerPaddingVertical,
        innerPaddingHorizontal = innerPaddingHorizontal,
        onSelect = onSelect
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewMultiStateIconSwitchTwoOptions() {
    val selectedIndexState = remember { mutableStateOf(0) }
    val icons = arrayOf(Icons.Rounded.Email, Icons.Rounded.AccountBox)
    val contentDescriptions = arrayOf("Email", "Account")
    MultiStateIconSwitch(
        icons = icons,
        contentDescriptions = contentDescriptions,
        selectedIndex = selectedIndexState.value,
        onSelect = {
            selectedIndexState.value = it
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewMultiStateIconSwitchFourOptions() {
    val selectedIndexState = remember { mutableStateOf(0) }
    val icons = arrayOf(Icons.Rounded.Email, Icons.Rounded.AccountBox, Icons.Rounded.Share, Icons.Rounded.Favorite)
    val contentDescriptions = arrayOf("Email", "Account", "Share", "Favorite")
    MultiStateIconSwitch(
        icons = icons,
        contentDescriptions = contentDescriptions,
        selectedIndex = selectedIndexState.value,
        onSelect = {
            selectedIndexState.value = it
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewMultiStateIconSwitchSevenOptions() {
    val selectedIndexState = remember { mutableStateOf(0) }
    val icons = arrayOf(Icons.Rounded.Email, Icons.Rounded.AccountBox, Icons.Rounded.Share, Icons.Rounded.Favorite, Icons.Rounded.ShoppingCart, Icons.Rounded.ExitToApp, Icons.Rounded.Face)
    val contentDescriptions = arrayOf("Email", "Account", "Share", "Favorite", "Shopping Cart", "Exit", "Face")
    MultiStateIconSwitch(
        icons = icons,
        contentDescriptions = contentDescriptions,
        selectedIndex = selectedIndexState.value,
        onSelect = {
            selectedIndexState.value = it
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewMultiStateIconSwitchSevenOptionsNoContentDescriptions() {
    val selectedIndexState = remember { mutableStateOf(0) }
    val icons = arrayOf(Icons.Rounded.Email, Icons.Rounded.AccountBox, Icons.Rounded.Share, Icons.Rounded.Favorite, Icons.Rounded.ShoppingCart, Icons.Rounded.ExitToApp, Icons.Rounded.Face)
    val contentDescriptions = arrayOf<String>()
    MultiStateIconSwitch(
        icons = icons,
        contentDescriptions = contentDescriptions,
        selectedIndex = selectedIndexState.value,
        onSelect = {
            selectedIndexState.value = it
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewMultiStateIconSwitchRowSevenOptions() {
    val selectedIndexState = remember { mutableStateOf(0) }
    val icons = arrayOf(Icons.Rounded.Email, Icons.Rounded.AccountBox, Icons.Rounded.Share, Icons.Rounded.Favorite, Icons.Rounded.ShoppingCart, Icons.Rounded.ExitToApp, Icons.Rounded.Face)
    val contentDescriptions = arrayOf("Email", "Account", "Share", "Favorite", "Shopping Cart", "Exit", "Face")
    MultiStateIconSwitchRow(
        icons = icons,
        contentDescriptions = contentDescriptions,
        selectedIndex = selectedIndexState.value,
        onSelect = {
            selectedIndexState.value = it
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewMultiStateIconSwitchColumnSevenOptions() {
    val selectedIndexState = remember { mutableStateOf(0) }
    val icons = arrayOf(Icons.Rounded.Email, Icons.Rounded.AccountBox, Icons.Rounded.Share, Icons.Rounded.Favorite, Icons.Rounded.ShoppingCart, Icons.Rounded.ExitToApp, Icons.Rounded.Face)
    val contentDescriptions = arrayOf("Email", "Account", "Share", "Favorite", "Shopping Cart", "Exit", "Face")
    MultiStateIconSwitchColumn(
        icons = icons,
        contentDescriptions = contentDescriptions,
        selectedIndex = selectedIndexState.value,
        onSelect = {
            selectedIndexState.value = it
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewMultiStateIconSwitchColumn3SevenOptions() {
    val selectedIndexState = remember { mutableStateOf(0) }
    val icons = arrayOf(Icons.Rounded.Email, Icons.Rounded.AccountBox, Icons.Rounded.Share, Icons.Rounded.Favorite, Icons.Rounded.ShoppingCart, Icons.Rounded.ExitToApp, Icons.Rounded.Face)
    val contentDescriptions = arrayOf("Email", "Account", "Share", "Favorite", "Shopping Cart", "Exit", "Face")
    MultiStateIconSwitch(
        icons = icons,
        contentDescriptions = contentDescriptions,
        selectedIndex = selectedIndexState.value,
        orientation = MultiStateSwitchOrientation.COLUMNS_3,
        onSelect = {
            selectedIndexState.value = it
        }
    )
}