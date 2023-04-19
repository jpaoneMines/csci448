package edu.mines.csci448.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

enum class MultiStateSwitchOrientation {
    HORIZONTAL,
    VERTICAL,
    COLUMNS_2,
    COLUMNS_3,
    COLUMNS_4,
    COLUMNS_5
}

@Composable
fun MultiStateSwitch(
    options: Array<@Composable () -> Unit>,
    selectedIndex: Int,
    orientation: MultiStateSwitchOrientation = MultiStateSwitchOrientation.HORIZONTAL,
    backgroundColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    outlineColor: Color = MaterialTheme.colorScheme.outline,
    selectedBackgroundColor: Color = MaterialTheme.colorScheme.primary,
    unselectedBackgroundColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    roundedCornerShapeSize: Dp = 24.dp,
    borderWidth: Dp = 2.dp,
    innerPaddingVertical: Dp = 8.dp,
    innerPaddingHorizontal: Dp = 16.dp,
    onSelect: (selectedIndex: Int) -> Unit
) {
    val numItems = options.size
    val numColumns = when (orientation) {
        MultiStateSwitchOrientation.HORIZONTAL -> numItems+1
        MultiStateSwitchOrientation.VERTICAL -> 1
        MultiStateSwitchOrientation.COLUMNS_2 -> 2
        MultiStateSwitchOrientation.COLUMNS_3 -> 3
        MultiStateSwitchOrientation.COLUMNS_4 -> 4
        MultiStateSwitchOrientation.COLUMNS_5 -> 5
    }
    val numLastRow = numItems % numColumns
    val numRows = (numItems / numColumns) + if(numLastRow > 0) {1} else {0}

    Column (
        modifier = Modifier
            .clip(shape = RoundedCornerShape(roundedCornerShapeSize))
            .background(backgroundColor)
            .border(
                width = borderWidth,
                color = outlineColor,
                shape = RoundedCornerShape(roundedCornerShapeSize)
            )
    ) {
        for (i in 0 until numRows) {
            Row{
                for (j in 0 until numColumns) {
                    if (i == numRows-1 && j > numLastRow-1) break

                    val index = i * numColumns + j
                    val optionComposable = options[index]

                    Box(
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(roundedCornerShapeSize))
                            .clickable { onSelect(index) }
                            .background(
                                if (index == selectedIndex) {
                                    selectedBackgroundColor
                                } else {
                                    unselectedBackgroundColor
                                }
                            )
                            .padding(vertical = innerPaddingVertical, horizontal = innerPaddingHorizontal)
                    ) {
                        optionComposable()
                    }
                }
            }
        }
    }
}

@Composable
fun MultiStateSwitchRow(
    options: Array<@Composable () -> Unit>,
    selectedIndex: Int,
    backgroundColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    outlineColor: Color = MaterialTheme.colorScheme.outline,
    selectedBackgroundColor: Color = MaterialTheme.colorScheme.primary,
    unselectedBackgroundColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    roundedCornerShapeSize: Dp = 24.dp,
    borderWidth: Dp = 2.dp,
    innerPaddingVertical: Dp = 8.dp,
    innerPaddingHorizontal: Dp = 16.dp,
    onSelect: (selectedIndex: Int) -> Unit
) {
    MultiStateSwitch(
        options = options,
        selectedIndex = selectedIndex,
        orientation = MultiStateSwitchOrientation.HORIZONTAL,
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
fun MultiStateSwitchColumn(
    options: Array<@Composable () -> Unit>,
    selectedIndex: Int,
    backgroundColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    outlineColor: Color = MaterialTheme.colorScheme.outline,
    selectedBackgroundColor: Color = MaterialTheme.colorScheme.primary,
    unselectedBackgroundColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    roundedCornerShapeSize: Dp = 24.dp,
    borderWidth: Dp = 2.dp,
    innerPaddingVertical: Dp = 16.dp,
    innerPaddingHorizontal: Dp = 8.dp,
    onSelect: (selectedIndex: Int) -> Unit
) {
    MultiStateSwitch(
        options = options,
        selectedIndex = selectedIndex,
        orientation = MultiStateSwitchOrientation.VERTICAL,
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

@Preview(showBackground = true)
@Composable
private fun PreviewMultiStateSwitchTwoTextOptions() {
    val selectedTextColor: Color = MaterialTheme.colorScheme.onPrimary
    val unselectedTextColor: Color = MaterialTheme.colorScheme.outline
    val selectedIndexState = remember { mutableStateOf(0) }
    val textContent: MutableList<@Composable () -> Unit> = mutableListOf()
    textContent += { Text(text = "Option 1", color = if(0 == selectedIndexState.value) { selectedTextColor } else { unselectedTextColor }) }
    textContent += { Text(text = "Option 2", color = if(1 == selectedIndexState.value) { selectedTextColor } else { unselectedTextColor }) }
    MultiStateSwitch(
        options = textContent.toTypedArray(),
        selectedIndex = selectedIndexState.value,
        onSelect = {
            selectedIndexState.value = it
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewMultiStateSwitchThreeTextOptions() {
    val selectedTextColor: Color = MaterialTheme.colorScheme.onPrimary
    val unselectedTextColor: Color = MaterialTheme.colorScheme.outline
    val selectedIndexState = remember { mutableStateOf(0) }
    val textContent: MutableList<@Composable () -> Unit> = mutableListOf()
    textContent += { Text(text = "Option 1", color = if(0 == selectedIndexState.value) { selectedTextColor } else { unselectedTextColor }) }
    textContent += { Text(text = "Option 2", color = if(1 == selectedIndexState.value) { selectedTextColor } else { unselectedTextColor }) }
    textContent += { Text(text = "Option 3", color = if(2 == selectedIndexState.value) { selectedTextColor } else { unselectedTextColor }) }
    MultiStateSwitch(
        options = textContent.toTypedArray(),
        selectedIndex = selectedIndexState.value,
        onSelect = {
            selectedIndexState.value = it
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewMultiStateSwitchFourTextOptions() {
    val selectedTextColor: Color = MaterialTheme.colorScheme.onPrimary
    val unselectedTextColor: Color = MaterialTheme.colorScheme.outline
    val selectedIndexState = remember { mutableStateOf(0) }
    val textContent: MutableList<@Composable () -> Unit> = mutableListOf()
    textContent += { Text(text = "Option 1", color = if(0 == selectedIndexState.value) { selectedTextColor } else { unselectedTextColor }) }
    textContent += { Text(text = "Option 2", color = if(1 == selectedIndexState.value) { selectedTextColor } else { unselectedTextColor }) }
    textContent += { Text(text = "Option 3", color = if(2 == selectedIndexState.value) { selectedTextColor } else { unselectedTextColor }) }
    textContent += { Text(text = "Option 4", color = if(3 == selectedIndexState.value) { selectedTextColor } else { unselectedTextColor }) }
    MultiStateSwitch(
        options = textContent.toTypedArray(),
        selectedIndex = selectedIndexState.value,
        onSelect = {
            selectedIndexState.value = it
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewMultiStateSwitchColumnFourTextOptions() {
    val selectedTextColor: Color = MaterialTheme.colorScheme.onPrimary
    val unselectedTextColor: Color = MaterialTheme.colorScheme.outline
    val selectedIndexState = remember { mutableStateOf(0) }
    val textContent: MutableList<@Composable () -> Unit> = mutableListOf()
    textContent += { Text(text = "Option 1", color = if(0 == selectedIndexState.value) { selectedTextColor } else { unselectedTextColor }) }
    textContent += { Text(text = "Option 2", color = if(1 == selectedIndexState.value) { selectedTextColor } else { unselectedTextColor }) }
    textContent += { Text(text = "Option 3", color = if(2 == selectedIndexState.value) { selectedTextColor } else { unselectedTextColor }) }
    textContent += { Text(text = "Option 4", color = if(3 == selectedIndexState.value) { selectedTextColor } else { unselectedTextColor }) }
    MultiStateSwitchColumn(
        options = textContent.toTypedArray(),
        selectedIndex = selectedIndexState.value,
        onSelect = {
            selectedIndexState.value = it
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewMultiStateSwitchTwoIconOptions() {
    val selectedIconColor: Color = MaterialTheme.colorScheme.onPrimary
    val unselectedIconColor: Color = MaterialTheme.colorScheme.outline
    val selectedIndexState = remember { mutableStateOf(0) }
    val iconContent: MutableList<@Composable () -> Unit> = mutableListOf()
    iconContent += { Icon(imageVector = Icons.Rounded.Email, contentDescription = null, tint = if(0 == selectedIndexState.value) { selectedIconColor } else { unselectedIconColor }) }
    iconContent += { Icon(imageVector = Icons.Rounded.AccountBox, contentDescription = null, tint = if(1 == selectedIndexState.value) { selectedIconColor } else { unselectedIconColor }) }
    MultiStateSwitch(
        options = iconContent.toTypedArray(),
        selectedIndex = selectedIndexState.value,
        onSelect = {
            selectedIndexState.value = it
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewMultiStateSwitchFourIconOptions() {
    val selectedIconColor: Color = MaterialTheme.colorScheme.onPrimary
    val unselectedIconColor: Color = MaterialTheme.colorScheme.outline
    val selectedIndexState = remember { mutableStateOf(0) }
    val iconContent: MutableList<@Composable () -> Unit> = mutableListOf()
    iconContent += { Icon(imageVector = Icons.Rounded.Email, contentDescription = null, tint = if(0 == selectedIndexState.value) { selectedIconColor } else { unselectedIconColor }) }
    iconContent += { Icon(imageVector = Icons.Rounded.AccountBox, contentDescription = null, tint = if(1 == selectedIndexState.value) { selectedIconColor } else { unselectedIconColor }) }
    iconContent += { Icon(imageVector = Icons.Rounded.Share, contentDescription = null, tint = if(2 == selectedIndexState.value) { selectedIconColor } else { unselectedIconColor }) }
    iconContent += { Icon(imageVector = Icons.Rounded.ShoppingCart, contentDescription = null, tint = if(3 == selectedIndexState.value) { selectedIconColor } else { unselectedIconColor }) }
    MultiStateSwitch(
        options = iconContent.toTypedArray(),
        selectedIndex = selectedIndexState.value,
        onSelect = {
            selectedIndexState.value = it
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewMultiStateSwitchSevenIconOptions() {
    val selectedIconColor: Color = MaterialTheme.colorScheme.onPrimary
    val unselectedIconColor: Color = MaterialTheme.colorScheme.outline
    val selectedIndexState = remember { mutableStateOf(0) }
    val iconContent: MutableList<@Composable () -> Unit> = mutableListOf()
    iconContent += { Icon(imageVector = Icons.Rounded.Email, contentDescription = null, tint = if(0 == selectedIndexState.value) { selectedIconColor } else { unselectedIconColor }) }
    iconContent += { Icon(imageVector = Icons.Rounded.AccountBox, contentDescription = null, tint = if(1 == selectedIndexState.value) { selectedIconColor } else { unselectedIconColor }) }
    iconContent += { Icon(imageVector = Icons.Rounded.Share, contentDescription = null, tint = if(2 == selectedIndexState.value) { selectedIconColor } else { unselectedIconColor }) }
    iconContent += { Icon(imageVector = Icons.Rounded.ShoppingCart, contentDescription = null, tint = if(3 == selectedIndexState.value) { selectedIconColor } else { unselectedIconColor }) }
    iconContent += { Icon(imageVector = Icons.Rounded.ExitToApp, contentDescription = null, tint = if(4 == selectedIndexState.value) { selectedIconColor } else { unselectedIconColor }) }
    iconContent += { Icon(imageVector = Icons.Rounded.Favorite, contentDescription = null, tint = if(5 == selectedIndexState.value) { selectedIconColor } else { unselectedIconColor }) }
    iconContent += { Icon(imageVector = Icons.Rounded.Face, contentDescription = null, tint = if(6 == selectedIndexState.value) { selectedIconColor } else { unselectedIconColor }) }
    MultiStateSwitch(
        options = iconContent.toTypedArray(),
        selectedIndex = selectedIndexState.value,
        onSelect = {
            selectedIndexState.value = it
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewMultiStateSwitchRowSevenIconOptions() {
    val selectedIconColor: Color = MaterialTheme.colorScheme.onPrimary
    val unselectedIconColor: Color = MaterialTheme.colorScheme.outline
    val selectedIndexState = remember { mutableStateOf(0) }
    val iconContent: MutableList<@Composable () -> Unit> = mutableListOf()
    iconContent += { Icon(imageVector = Icons.Rounded.Email, contentDescription = null, tint = if(0 == selectedIndexState.value) { selectedIconColor } else { unselectedIconColor }) }
    iconContent += { Icon(imageVector = Icons.Rounded.AccountBox, contentDescription = null, tint = if(1 == selectedIndexState.value) { selectedIconColor } else { unselectedIconColor }) }
    iconContent += { Icon(imageVector = Icons.Rounded.Share, contentDescription = null, tint = if(2 == selectedIndexState.value) { selectedIconColor } else { unselectedIconColor }) }
    iconContent += { Icon(imageVector = Icons.Rounded.ShoppingCart, contentDescription = null, tint = if(3 == selectedIndexState.value) { selectedIconColor } else { unselectedIconColor }) }
    iconContent += { Icon(imageVector = Icons.Rounded.ExitToApp, contentDescription = null, tint = if(4 == selectedIndexState.value) { selectedIconColor } else { unselectedIconColor }) }
    iconContent += { Icon(imageVector = Icons.Rounded.Favorite, contentDescription = null, tint = if(5 == selectedIndexState.value) { selectedIconColor } else { unselectedIconColor }) }
    iconContent += { Icon(imageVector = Icons.Rounded.Face, contentDescription = null, tint = if(6 == selectedIndexState.value) { selectedIconColor } else { unselectedIconColor }) }
    MultiStateSwitchRow(
        options = iconContent.toTypedArray(),
        selectedIndex = selectedIndexState.value,
        onSelect = {
            selectedIndexState.value = it
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewMultiStateSwitchColumnSevenIconOptions() {
    val selectedIconColor: Color = MaterialTheme.colorScheme.onPrimary
    val unselectedIconColor: Color = MaterialTheme.colorScheme.outline
    val selectedIndexState = remember { mutableStateOf(0) }
    val iconContent: MutableList<@Composable () -> Unit> = mutableListOf()
    iconContent += { Icon(imageVector = Icons.Rounded.Email, contentDescription = null, tint = if(0 == selectedIndexState.value) { selectedIconColor } else { unselectedIconColor }) }
    iconContent += { Icon(imageVector = Icons.Rounded.AccountBox, contentDescription = null, tint = if(1 == selectedIndexState.value) { selectedIconColor } else { unselectedIconColor }) }
    iconContent += { Icon(imageVector = Icons.Rounded.Share, contentDescription = null, tint = if(2 == selectedIndexState.value) { selectedIconColor } else { unselectedIconColor }) }
    iconContent += { Icon(imageVector = Icons.Rounded.ShoppingCart, contentDescription = null, tint = if(3 == selectedIndexState.value) { selectedIconColor } else { unselectedIconColor }) }
    iconContent += { Icon(imageVector = Icons.Rounded.ExitToApp, contentDescription = null, tint = if(4 == selectedIndexState.value) { selectedIconColor } else { unselectedIconColor }) }
    iconContent += { Icon(imageVector = Icons.Rounded.Favorite, contentDescription = null, tint = if(5 == selectedIndexState.value) { selectedIconColor } else { unselectedIconColor }) }
    iconContent += { Icon(imageVector = Icons.Rounded.Face, contentDescription = null, tint = if(6 == selectedIndexState.value) { selectedIconColor } else { unselectedIconColor }) }
    MultiStateSwitchColumn(
        options = iconContent.toTypedArray(),
        selectedIndex = selectedIndexState.value,
        onSelect = {
            selectedIndexState.value = it
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewMultiStateSwitchSquareSevenIconOptions() {
    val selectedIconColor: Color = MaterialTheme.colorScheme.onPrimary
    val unselectedIconColor: Color = MaterialTheme.colorScheme.outline
    val selectedIndexState = remember { mutableStateOf(0) }
    val iconContent: MutableList<@Composable () -> Unit> = mutableListOf()
    iconContent += { Icon(imageVector = Icons.Rounded.Email, contentDescription = null, tint = if(0 == selectedIndexState.value) { selectedIconColor } else { unselectedIconColor }) }
    iconContent += { Icon(imageVector = Icons.Rounded.AccountBox, contentDescription = null, tint = if(1 == selectedIndexState.value) { selectedIconColor } else { unselectedIconColor }) }
    iconContent += { Icon(imageVector = Icons.Rounded.Share, contentDescription = null, tint = if(2 == selectedIndexState.value) { selectedIconColor } else { unselectedIconColor }) }
    iconContent += { Icon(imageVector = Icons.Rounded.ShoppingCart, contentDescription = null, tint = if(3 == selectedIndexState.value) { selectedIconColor } else { unselectedIconColor }) }
    iconContent += { Icon(imageVector = Icons.Rounded.ExitToApp, contentDescription = null, tint = if(4 == selectedIndexState.value) { selectedIconColor } else { unselectedIconColor }) }
    iconContent += { Icon(imageVector = Icons.Rounded.Favorite, contentDescription = null, tint = if(5 == selectedIndexState.value) { selectedIconColor } else { unselectedIconColor }) }
    iconContent += { Icon(imageVector = Icons.Rounded.Face, contentDescription = null, tint = if(6 == selectedIndexState.value) { selectedIconColor } else { unselectedIconColor }) }
    MultiStateSwitch(
        options = iconContent.toTypedArray(),
        selectedIndex = selectedIndexState.value,
        roundedCornerShapeSize = 0.dp,
        onSelect = {
            selectedIndexState.value = it
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewMultiStateSwitchGrid2ColSevenIconOptions() {
    val selectedIconColor: Color = MaterialTheme.colorScheme.onPrimary
    val unselectedIconColor: Color = MaterialTheme.colorScheme.outline
    val selectedIndexState = remember { mutableStateOf(0) }
    val iconContent: MutableList<@Composable () -> Unit> = mutableListOf()
    iconContent += { Icon(imageVector = Icons.Rounded.Email, contentDescription = null, tint = if(0 == selectedIndexState.value) { selectedIconColor } else { unselectedIconColor }) }
    iconContent += { Icon(imageVector = Icons.Rounded.AccountBox, contentDescription = null, tint = if(1 == selectedIndexState.value) { selectedIconColor } else { unselectedIconColor }) }
    iconContent += { Icon(imageVector = Icons.Rounded.Share, contentDescription = null, tint = if(2 == selectedIndexState.value) { selectedIconColor } else { unselectedIconColor }) }
    iconContent += { Icon(imageVector = Icons.Rounded.ShoppingCart, contentDescription = null, tint = if(3 == selectedIndexState.value) { selectedIconColor } else { unselectedIconColor }) }
    iconContent += { Icon(imageVector = Icons.Rounded.ExitToApp, contentDescription = null, tint = if(4 == selectedIndexState.value) { selectedIconColor } else { unselectedIconColor }) }
    iconContent += { Icon(imageVector = Icons.Rounded.Favorite, contentDescription = null, tint = if(5 == selectedIndexState.value) { selectedIconColor } else { unselectedIconColor }) }
    iconContent += { Icon(imageVector = Icons.Rounded.Face, contentDescription = null, tint = if(6 == selectedIndexState.value) { selectedIconColor } else { unselectedIconColor }) }
    MultiStateSwitch(
        options = iconContent.toTypedArray(),
        selectedIndex = selectedIndexState.value,
        orientation = MultiStateSwitchOrientation.COLUMNS_2,
        onSelect = {
            selectedIndexState.value = it
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewMultiStateSwitchGrid3ColSevenIconOptions() {
    val selectedIconColor: Color = MaterialTheme.colorScheme.onPrimary
    val unselectedIconColor: Color = MaterialTheme.colorScheme.outline
    val selectedIndexState = remember { mutableStateOf(0) }
    val iconContent: MutableList<@Composable () -> Unit> = mutableListOf()
    iconContent += { Icon(imageVector = Icons.Rounded.Email, contentDescription = null, tint = if(0 == selectedIndexState.value) { selectedIconColor } else { unselectedIconColor }) }
    iconContent += { Icon(imageVector = Icons.Rounded.AccountBox, contentDescription = null, tint = if(1 == selectedIndexState.value) { selectedIconColor } else { unselectedIconColor }) }
    iconContent += { Icon(imageVector = Icons.Rounded.Share, contentDescription = null, tint = if(2 == selectedIndexState.value) { selectedIconColor } else { unselectedIconColor }) }
    iconContent += { Icon(imageVector = Icons.Rounded.ShoppingCart, contentDescription = null, tint = if(3 == selectedIndexState.value) { selectedIconColor } else { unselectedIconColor }) }
    iconContent += { Icon(imageVector = Icons.Rounded.ExitToApp, contentDescription = null, tint = if(4 == selectedIndexState.value) { selectedIconColor } else { unselectedIconColor }) }
    iconContent += { Icon(imageVector = Icons.Rounded.Favorite, contentDescription = null, tint = if(5 == selectedIndexState.value) { selectedIconColor } else { unselectedIconColor }) }
    iconContent += { Icon(imageVector = Icons.Rounded.Face, contentDescription = null, tint = if(6 == selectedIndexState.value) { selectedIconColor } else { unselectedIconColor }) }
    MultiStateSwitch(
        options = iconContent.toTypedArray(),
        selectedIndex = selectedIndexState.value,
        orientation = MultiStateSwitchOrientation.COLUMNS_3,
        onSelect = {
            selectedIndexState.value = it
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewMultiStateSwitchGrid4ColSevenIconOptions() {
    val selectedIconColor: Color = MaterialTheme.colorScheme.onPrimary
    val unselectedIconColor: Color = MaterialTheme.colorScheme.outline
    val selectedIndexState = remember { mutableStateOf(0) }
    val iconContent: MutableList<@Composable () -> Unit> = mutableListOf()
    iconContent += { Icon(imageVector = Icons.Rounded.Email, contentDescription = null, tint = if(0 == selectedIndexState.value) { selectedIconColor } else { unselectedIconColor }) }
    iconContent += { Icon(imageVector = Icons.Rounded.AccountBox, contentDescription = null, tint = if(1 == selectedIndexState.value) { selectedIconColor } else { unselectedIconColor }) }
    iconContent += { Icon(imageVector = Icons.Rounded.Share, contentDescription = null, tint = if(2 == selectedIndexState.value) { selectedIconColor } else { unselectedIconColor }) }
    iconContent += { Icon(imageVector = Icons.Rounded.ShoppingCart, contentDescription = null, tint = if(3 == selectedIndexState.value) { selectedIconColor } else { unselectedIconColor }) }
    iconContent += { Icon(imageVector = Icons.Rounded.ExitToApp, contentDescription = null, tint = if(4 == selectedIndexState.value) { selectedIconColor } else { unselectedIconColor }) }
    iconContent += { Icon(imageVector = Icons.Rounded.Favorite, contentDescription = null, tint = if(5 == selectedIndexState.value) { selectedIconColor } else { unselectedIconColor }) }
    iconContent += { Icon(imageVector = Icons.Rounded.Face, contentDescription = null, tint = if(6 == selectedIndexState.value) { selectedIconColor } else { unselectedIconColor }) }
    MultiStateSwitch(
        options = iconContent.toTypedArray(),
        selectedIndex = selectedIndexState.value,
        orientation = MultiStateSwitchOrientation.COLUMNS_4,
        onSelect = {
            selectedIndexState.value = it
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewMultiStateSwitchGrid5ColSevenIconOptions() {
    val selectedIconColor: Color = MaterialTheme.colorScheme.onPrimary
    val unselectedIconColor: Color = MaterialTheme.colorScheme.outline
    val selectedIndexState = remember { mutableStateOf(0) }
    val iconContent: MutableList<@Composable () -> Unit> = mutableListOf()
    iconContent += { Icon(imageVector = Icons.Rounded.Email, contentDescription = null, tint = if(0 == selectedIndexState.value) { selectedIconColor } else { unselectedIconColor }) }
    iconContent += { Icon(imageVector = Icons.Rounded.AccountBox, contentDescription = null, tint = if(1 == selectedIndexState.value) { selectedIconColor } else { unselectedIconColor }) }
    iconContent += { Icon(imageVector = Icons.Rounded.Share, contentDescription = null, tint = if(2 == selectedIndexState.value) { selectedIconColor } else { unselectedIconColor }) }
    iconContent += { Icon(imageVector = Icons.Rounded.ShoppingCart, contentDescription = null, tint = if(3 == selectedIndexState.value) { selectedIconColor } else { unselectedIconColor }) }
    iconContent += { Icon(imageVector = Icons.Rounded.ExitToApp, contentDescription = null, tint = if(4 == selectedIndexState.value) { selectedIconColor } else { unselectedIconColor }) }
    iconContent += { Icon(imageVector = Icons.Rounded.Favorite, contentDescription = null, tint = if(5 == selectedIndexState.value) { selectedIconColor } else { unselectedIconColor }) }
    iconContent += { Icon(imageVector = Icons.Rounded.Face, contentDescription = null, tint = if(6 == selectedIndexState.value) { selectedIconColor } else { unselectedIconColor }) }
    MultiStateSwitch(
        options = iconContent.toTypedArray(),
        selectedIndex = selectedIndexState.value,
        orientation = MultiStateSwitchOrientation.COLUMNS_5,
        onSelect = {
            selectedIndexState.value = it
        }
    )
}