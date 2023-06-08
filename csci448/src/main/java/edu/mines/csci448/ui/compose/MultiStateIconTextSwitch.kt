package edu.mines.csci448.ui.compose

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

@Composable
fun MultiStateIconTextSwitch(
    icons: Array<ImageVector>,
    contentDescriptions: Array<String>,
    selectedIndex: Int,
    orientation: MultiStateSwitchOrientation = MultiStateSwitchOrientation.HORIZONTAL,
    backgroundColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    outlineColor: Color = MaterialTheme.colorScheme.outline,
    fontSize: TextUnit = TextUnit.Unspecified,
    selectedIconColor: Color = MaterialTheme.colorScheme.onPrimary,
    selectedTextColor: Color = MaterialTheme.colorScheme.onPrimary,
    selectedBackgroundColor: Color = MaterialTheme.colorScheme.primary,
    unselectedIconColor: Color = MaterialTheme.colorScheme.outline,
    unselectedTextColor: Color = MaterialTheme.colorScheme.outline,
    unselectedBackgroundColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    roundedCornerShapeSize: Dp = 24.dp,
    borderWidth: Dp = 2.dp,
    innerPaddingVertical: Dp = 16.dp,
    innerPaddingHorizontal: Dp = 16.dp,
    contentAlignment: Alignment = Alignment.CenterStart,
    onSelect: (selectedIndex: Int) -> Unit
) {
    val iconContent: MutableList<@Composable () -> Unit> = mutableListOf()
    icons.forEachIndexed { index, iconVector ->
        val contentDescription = if(index < contentDescriptions.size) { contentDescriptions[index] } else { null }
        iconContent += {
            Row(
                verticalAlignment = CenterVertically
            ) {
                Icon(
                    imageVector = iconVector,
                    contentDescription = contentDescription,
                    tint = if(index == selectedIndex) { selectedIconColor } else { unselectedIconColor }
                )
                if (contentDescription != null) {
                    Spacer(modifier = Modifier.width(innerPaddingHorizontal/2))
                    Text(
                        text = contentDescription,
                        fontSize = fontSize,
                        color = if(index == selectedIndex) { selectedTextColor } else { unselectedTextColor }
                    )
                }
            }
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
        contentAlignment = contentAlignment,
        onSelect = onSelect
    )
}

@Composable
fun MultiStateIconTextSwitchRow(
    icons: Array<ImageVector>,
    contentDescriptions: Array<String>,
    selectedIndex: Int,
    backgroundColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    outlineColor: Color = MaterialTheme.colorScheme.outline,
    fontSize: TextUnit = TextUnit.Unspecified,
    selectedIconColor: Color = MaterialTheme.colorScheme.onPrimary,
    selectedTextColor: Color = MaterialTheme.colorScheme.onPrimary,
    selectedBackgroundColor: Color = MaterialTheme.colorScheme.primary,
    unselectedIconColor: Color = MaterialTheme.colorScheme.outline,
    unselectedTextColor: Color = MaterialTheme.colorScheme.outline,
    unselectedBackgroundColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    roundedCornerShapeSize: Dp = 24.dp,
    borderWidth: Dp = 2.dp,
    innerPaddingVertical: Dp = 16.dp,
    innerPaddingHorizontal: Dp = 16.dp,
    contentAlignment: Alignment = Alignment.CenterStart,
    onSelect: (selectedIndex: Int) -> Unit
) {
    MultiStateIconTextSwitch(
        icons = icons,
        contentDescriptions = contentDescriptions,
        selectedIndex = selectedIndex,
        orientation = MultiStateSwitchOrientation.HORIZONTAL,
        backgroundColor = backgroundColor,
        outlineColor = outlineColor,
        fontSize = fontSize,
        selectedIconColor = selectedIconColor,
        selectedTextColor = selectedTextColor,
        selectedBackgroundColor = selectedBackgroundColor,
        unselectedIconColor = unselectedIconColor,
        unselectedTextColor = unselectedTextColor,
        unselectedBackgroundColor = unselectedBackgroundColor,
        roundedCornerShapeSize = roundedCornerShapeSize,
        borderWidth = borderWidth,
        innerPaddingVertical = innerPaddingVertical,
        innerPaddingHorizontal = innerPaddingHorizontal,
        contentAlignment = contentAlignment,
        onSelect = onSelect
    )
}

@Composable
fun MultiStateIconTextSwitchColumn(
    icons: Array<ImageVector>,
    contentDescriptions: Array<String>,
    selectedIndex: Int,
    backgroundColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    outlineColor: Color = MaterialTheme.colorScheme.outline,
    fontSize: TextUnit = TextUnit.Unspecified,
    selectedIconColor: Color = MaterialTheme.colorScheme.onPrimary,
    selectedTextColor: Color = MaterialTheme.colorScheme.onPrimary,
    selectedBackgroundColor: Color = MaterialTheme.colorScheme.primary,
    unselectedIconColor: Color = MaterialTheme.colorScheme.outline,
    unselectedTextColor: Color = MaterialTheme.colorScheme.outline,
    unselectedBackgroundColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    roundedCornerShapeSize: Dp = 24.dp,
    borderWidth: Dp = 2.dp,
    innerPaddingVertical: Dp = 16.dp,
    innerPaddingHorizontal: Dp = 16.dp,
    contentAlignment: Alignment = Alignment.CenterStart,
    onSelect: (selectedIndex: Int) -> Unit
) {
    MultiStateIconTextSwitch(
        icons = icons,
        contentDescriptions = contentDescriptions,
        selectedIndex = selectedIndex,
        orientation = MultiStateSwitchOrientation.VERTICAL,
        backgroundColor = backgroundColor,
        outlineColor = outlineColor,
        fontSize = fontSize,
        selectedIconColor = selectedIconColor,
        selectedTextColor = selectedTextColor,
        selectedBackgroundColor = selectedBackgroundColor,
        unselectedIconColor = unselectedIconColor,
        unselectedTextColor = unselectedTextColor,
        unselectedBackgroundColor = unselectedBackgroundColor,
        roundedCornerShapeSize = roundedCornerShapeSize,
        borderWidth = borderWidth,
        innerPaddingVertical = innerPaddingVertical,
        innerPaddingHorizontal = innerPaddingHorizontal,
        contentAlignment = contentAlignment,
        onSelect = onSelect
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewMultiStateIconTextSwitchTwoOptions() {
    val selectedIndexState = remember { mutableStateOf(0) }
    val icons = arrayOf(Icons.Rounded.Email, Icons.Rounded.AccountBox)
    val contentDescriptions = arrayOf("Email", "Account")
    MultiStateIconTextSwitch(
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
private fun PreviewMultiStateIconTextSwitchRowTwoOptions() {
    val selectedIndexState = remember { mutableStateOf(0) }
    val icons = arrayOf(Icons.Rounded.Email, Icons.Rounded.AccountBox)
    val contentDescriptions = arrayOf("Email", "Account")
    MultiStateIconTextSwitchRow(
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
private fun PreviewMultiStateIconTextSwitchSevenOptionsNoContentDescriptions() {
    val selectedIndexState = remember { mutableStateOf(0) }
    val icons = arrayOf(Icons.Rounded.Email, Icons.Rounded.AccountBox, Icons.Rounded.Share, Icons.Rounded.Favorite, Icons.Rounded.ShoppingCart, Icons.Rounded.ExitToApp, Icons.Rounded.Face)
    val contentDescriptions = arrayOf<String>()
    MultiStateIconTextSwitch(
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
private fun PreviewMultiStateIconTextSwitchColumnSevenOptions() {
    val selectedIndexState = remember { mutableStateOf(0) }
    val icons = arrayOf(Icons.Rounded.Email, Icons.Rounded.AccountBox, Icons.Rounded.Share, Icons.Rounded.Favorite, Icons.Rounded.ShoppingCart, Icons.Rounded.ExitToApp, Icons.Rounded.Face)
    val contentDescriptions = arrayOf("Email", "Account", "Share", "Favorite", "Shopping Cart", "Exit", "Face")
    MultiStateIconTextSwitchColumn(
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
private fun PreviewMultiStateIconTextSwitchColumn3SevenOptions() {
    val selectedIndexState = remember { mutableStateOf(0) }
    val icons = arrayOf(Icons.Rounded.Email, Icons.Rounded.AccountBox, Icons.Rounded.Share, Icons.Rounded.Favorite, Icons.Rounded.ShoppingCart, Icons.Rounded.ExitToApp, Icons.Rounded.Face)
    val contentDescriptions = arrayOf("Email", "Account", "Share", "Favorite", "Shopping Cart", "Exit", "Face")
    MultiStateIconTextSwitch(
        icons = icons,
        contentDescriptions = contentDescriptions,
        selectedIndex = selectedIndexState.value,
        orientation = MultiStateSwitchOrientation.COLUMNS_3,
        onSelect = {
            selectedIndexState.value = it
        }
    )
}