package edu.mines.csci448.ui.compose

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

@Composable
fun MultiStateTextSwitch(
    labels: Array<String>,
    selectedIndex: Int,
    orientation: MultiStateSwitchOrientation = MultiStateSwitchOrientation.HORIZONTAL,
    backgroundColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    outlineColor: Color = MaterialTheme.colorScheme.outline,
    fontSize: TextUnit = TextUnit.Unspecified,
    selectedTextColor: Color = MaterialTheme.colorScheme.onPrimary,
    selectedBackgroundColor: Color = MaterialTheme.colorScheme.primary,
    unselectedTextColor: Color = MaterialTheme.colorScheme.outline,
    unselectedBackgroundColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    roundedCornerShapeSize: Dp = 24.dp,
    borderWidth: Dp = 2.dp,
    innerPaddingVertical: Dp = 8.dp,
    innerPaddingHorizontal: Dp = 16.dp,
    onSelect: (selectedIndex: Int) -> Unit
) {
    val textContent: MutableList<@Composable () -> Unit> = mutableListOf()
    labels.forEachIndexed { index, label ->
        textContent += {
            Text(
                text = label,
                fontSize = fontSize,
                color = if(index == selectedIndex) { selectedTextColor } else { unselectedTextColor }
            )
        }
    }
    MultiStateSwitch(
        options = textContent.toTypedArray(),
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
fun MultiStateTextSwitchRow(
    labels: Array<String>,
    selectedIndex: Int,
    backgroundColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    outlineColor: Color = MaterialTheme.colorScheme.outline,
    fontSize: TextUnit = TextUnit.Unspecified,
    selectedTextColor: Color = MaterialTheme.colorScheme.onPrimary,
    selectedBackgroundColor: Color = MaterialTheme.colorScheme.primary,
    unselectedTextColor: Color = MaterialTheme.colorScheme.outline,
    unselectedBackgroundColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    roundedCornerShapeSize: Dp = 24.dp,
    borderWidth: Dp = 2.dp,
    innerPaddingVertical: Dp = 8.dp,
    innerPaddingHorizontal: Dp = 16.dp,
    onSelect: (selectedIndex: Int) -> Unit
) {
    MultiStateTextSwitch(
        labels = labels,
        selectedIndex = selectedIndex,
        orientation = MultiStateSwitchOrientation.HORIZONTAL,
        backgroundColor = backgroundColor,
        outlineColor = outlineColor,
        fontSize = fontSize,
        selectedTextColor = selectedTextColor,
        selectedBackgroundColor = selectedBackgroundColor,
        unselectedTextColor = unselectedTextColor,
        unselectedBackgroundColor = unselectedBackgroundColor,
        roundedCornerShapeSize = roundedCornerShapeSize,
        borderWidth = borderWidth,
        innerPaddingVertical = innerPaddingVertical,
        innerPaddingHorizontal = innerPaddingHorizontal,
        onSelect = onSelect
    )
}

@Composable
fun MultiStateTextSwitchColumn(
    labels: Array<String>,
    selectedIndex: Int,
    backgroundColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    outlineColor: Color = MaterialTheme.colorScheme.outline,
    fontSize: TextUnit = TextUnit.Unspecified,
    selectedTextColor: Color = MaterialTheme.colorScheme.onPrimary,
    selectedBackgroundColor: Color = MaterialTheme.colorScheme.primary,
    unselectedTextColor: Color = MaterialTheme.colorScheme.outline,
    unselectedBackgroundColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    roundedCornerShapeSize: Dp = 24.dp,
    borderWidth: Dp = 2.dp,
    innerPaddingVertical: Dp = 8.dp,
    innerPaddingHorizontal: Dp = 16.dp,
    onSelect: (selectedIndex: Int) -> Unit
) {
    MultiStateTextSwitch(
        labels = labels,
        selectedIndex = selectedIndex,
        orientation = MultiStateSwitchOrientation.VERTICAL,
        backgroundColor = backgroundColor,
        outlineColor = outlineColor,
        fontSize = fontSize,
        selectedTextColor = selectedTextColor,
        selectedBackgroundColor = selectedBackgroundColor,
        unselectedTextColor = unselectedTextColor,
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
private fun PreviewMultiStateSwitchTwoOptions() {
    val labels = arrayOf("Option 1", "Option 2")
    val selectedIndexState = remember { mutableStateOf(0) }
    MultiStateTextSwitch(
        labels = labels,
        selectedIndex = selectedIndexState.value,
        onSelect = {
            selectedIndexState.value = it
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewMultiStateSwitchThreeOptions() {
    val labels = arrayOf("Option 1", "Option 2", "Option 3")
    val selectedIndexState = remember { mutableStateOf(0) }
    MultiStateTextSwitch(
        labels = labels,
        selectedIndex = selectedIndexState.value,
        onSelect = {
            selectedIndexState.value = it
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewMultiStateSwitchFourOptions() {
    val labels = arrayOf("Option 1", "Option 2", "Option 3", "Option 4")
    val selectedIndexState = remember { mutableStateOf(0) }
    MultiStateTextSwitch(
        labels = labels,
        selectedIndex = selectedIndexState.value,
        onSelect = {
            selectedIndexState.value = it
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewMultiStateSwitchRowFourOptions() {
    val labels = arrayOf("Option 1", "Option 2", "Option 3", "Option 4")
    val selectedIndexState = remember { mutableStateOf(0) }
    MultiStateTextSwitchRow(
        labels = labels,
        selectedIndex = selectedIndexState.value,
        onSelect = {
            selectedIndexState.value = it
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewMultiStateSwitchColumnFourOptions() {
    val labels = arrayOf("Option 1", "Option 2", "Option 3", "Option 4")
    val selectedIndexState = remember { mutableStateOf(0) }
    MultiStateTextSwitchColumn(
        labels = labels,
        selectedIndex = selectedIndexState.value,
        onSelect = {
            selectedIndexState.value = it
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewMultiStateSwitchFiveOptions() {
    val labels = arrayOf("T1", "T2", "T3", "T4", "T5")
    val selectedIndexState = remember { mutableStateOf(0) }
    MultiStateTextSwitch(
        labels = labels,
        selectedIndex = selectedIndexState.value,
        onSelect = {
            selectedIndexState.value = it
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewMultiStateSwitchFourOptionsDifferentColors() {
    val labels = arrayOf("Option 1", "Option 2", "Option 3", "Option 4")
    val selectedIndexState = remember { mutableStateOf(0) }
    MultiStateTextSwitch(
        labels = labels,
        selectedIndex = selectedIndexState.value,
        onSelect = {
            selectedIndexState.value = it
        },
        selectedBackgroundColor = Color.Black,
        unselectedBackgroundColor = Color.Green,
        backgroundColor = Color.Green,
        selectedTextColor = Color.Green,
        unselectedTextColor = Color.Black,
        outlineColor = Color.Black
    )
}