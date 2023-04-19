package edu.mines.csci448

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import edu.mines.csci448.ui.compose.MultiStateSwitch
import edu.mines.csci448.ui.theme.CSCI448Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CSCI448Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val switchState = remember { mutableStateOf(0) }
                    MultiStateSwitch(
                        options = arrayOf(
                            { Text(text = "One") },
                            { Text(text = "Two") },
                            { Text(text = "Three") }
                        ),
                        selectedIndex = switchState.value,
                        onSelect = { switchState.value = it }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CSCI448Theme {
        val switchState = remember { mutableStateOf(0) }
        MultiStateSwitch(
            options = arrayOf(
                { Text(text = "One") },
                { Text(text = "Two") },
                { Text(text = "Three") }
            ),
            selectedIndex = switchState.value,
            onSelect = { switchState.value = it }
        )
    }
}