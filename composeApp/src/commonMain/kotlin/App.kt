import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import di.initializeKoin
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import presentation.screen.HomeScreen

import ui.theme.darkScheme
import ui.theme.lightScheme

@Composable
@Preview
fun App() {

    val colors = if (!isSystemInDarkTheme()) lightScheme else darkScheme

    MaterialTheme(colorScheme = colors) {
        Navigator(HomeScreen())


    }
}