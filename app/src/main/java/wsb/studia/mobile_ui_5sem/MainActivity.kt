package wsb.studia.mobile_ui_5sem

import android.R
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import wsb.studia.mobile_ui_5sem.ui.theme.UIProjektZaliczenieTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UIProjektZaliczenieTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppContent(modifier = Modifier.padding(innerPadding))
                }
            }
        }
        //todo add onBackPressedListener that exist app rather than just the activity
    }
}

@Composable
private fun AppContent(modifier: Modifier = Modifier) {
    var shouldShowWelcome by rememberSaveable { mutableStateOf(false) }

    Surface(modifier) {
        if (shouldShowWelcome) {
            // overlap WelcomeContent over main screen
            Overlay2(modifier = Modifier.fillMaxSize()) {
                WelcomeContent(onContinueClicked = { shouldShowWelcome = false })
                HomeContent(modifier = modifier)
            }
        } else {
            // just main screen
            HomeContent(modifier = modifier)
        }
    }
}

/**
 * Overlays two layouts on top of each other. The first layout is in the foreground, the second is in the bottom.
 *
 * Exapmle use:
 * ```
 * Overlay2(modifier = Modifier.fillMaxSize()) {
 *     OverlayContent(modifier = modifier.padding(top = 24.dp))
 *     MainContent(modifier = modifier)
 * }
 * ```
 *
 * @param modifier modifier for the whole layout
 * @param content list of layouts to be displayed, can be presented as a lambda
 */
@Composable
//todo check if we need this elsewhere, if yes - move to helper class
private fun Overlay2(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(modifier = modifier, content = content) {
        //todo add overlay logic
            measurables, constraints ->
        val upper = measurables[0]
        val lower = measurables[1]
        val looseConstraints = constraints.copy(
            minWidth = 0,
            minHeight = 0,
        )
        val upperPlaceable = upper.measure(looseConstraints)
        val lowerPlaceable = lower.measure(looseConstraints)
        layout(
            width = constraints.maxWidth,
            height = constraints.maxHeight
        ) {
            //lowerPlaceable.place((constraints.maxWidth - lowerPlaceable.width)/2, (constraints.maxHeight - lowerPlaceable.height)/2)
            lowerPlaceable.place(0, 0)
            upperPlaceable.place(0, 0)
        }
    }
}

@Composable
private fun HomeContent(modifier: Modifier = Modifier) {
//    Text("UwU", fontSize = 128.sp)
    ButtonGrid(Modifier)
}

@Composable
fun ButtonGrid(modifier: Modifier = Modifier) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(64.dp, Alignment.CenterVertically)
        ) {
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(64.dp, Alignment.CenterHorizontally)
            ) {
                LabelledIconButton(iconResource = R.drawable.ic_dialog_alert, label = "Camera")
                LabelledIconButton(iconResource = R.drawable.ic_dialog_alert, label = "Camera")
            }
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(64.dp, Alignment.CenterHorizontally)
            ) {
                LabelledIconButton(iconResource = R.drawable.ic_dialog_alert, label = "Camera")
                LabelledIconButton(iconResource = R.drawable.ic_dialog_alert, label = "Camera")
            }
        }
    }
}

@Composable
fun LabelledIconButton(
    modifier: Modifier = Modifier,
    iconResource: Int,
    label: String
) {
    Column(modifier = modifier) {
        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
            shape = RoundedCornerShape(size = 16.dp),
            modifier = modifier.size(96.dp)
        ) {
            Icon(
                painter = painterResource(id = iconResource),
                contentDescription = null,
                modifier = modifier.fillMaxSize(0.75f),
                tint = MaterialTheme.colorScheme.primary
            )
        }
        Text(
            text = label,
            fontSize = MaterialTheme.typography.labelSmall.fontSize,
            modifier = modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun LabelledIconButtonPreview() {
    LabelledIconButton(iconResource = android.R.drawable.ic_dialog_alert, label = "Camera")
}

@Preview(showBackground = true)
@Composable
private fun HomeContentPreview() {
    HomeContent()
}


// below is stuff for welcome screen

@Composable
private fun WelcomeContent(
    modifier: Modifier = Modifier,
    onContinueClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color(0x99000000))
                    .padding(top = 55.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(bottom = 96.dp)
                        .fillMaxWidth()
                        .background(
                            color = MaterialTheme.colorScheme.outlineVariant,
                        )
                        .padding(vertical = 35.dp)
                ) {
                    Text("Witaj w Nutri-Spy",
                        fontSize = 40.sp,
                        textAlign = TextAlign.Center,
                        //fontFamily = FontFamily.,
                        modifier = Modifier
                            .padding(bottom = 33.dp,start = 37.dp,end = 37.dp,)
                    )
                    Text("Najważniejsze rzeczy  które musisz wiedzieć przed użyciem aplikacji",
                        fontSize = 11.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(bottom = 6.dp,start = 23.dp,end = 23.dp,)
                    )
                    Column(
                        modifier = Modifier
                            .padding(bottom = 9.dp,)
                            .height(1.dp)
                            .fillMaxWidth()
                            .background(
                                color = Color(0xFF000000),
                            )
                    ) { }
                    Text("Aplikacja Nutri-Spy jest w fazie testów. Nie jest publicznie dostępna. Wszelkie problemy proszę zgłaszać do Grzegorza\u00a0Widery i\u00a0Szymona\u00a0Gorczycy",
                        fontSize = 11.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(bottom = 25.dp,start = 50.dp,end = 50.dp,)
                    )
                    Text("Nutri-Spy skanuje kody kreskowe wybranych produktów spożywczych a następnie pokazuje ich skład w celu uświadomienia użytkownika o ilości i rodzaju składników, które zawiera produkt",
                        fontSize = 11.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(horizontal = 26.dp,)
                    )
                }
                //ok button
                ConfirmationButton(modifier, onContinueClicked)
            }
        }
    }
}

@Composable
private fun ConfirmationButton(modifier: Modifier = Modifier, onContinueClicked: () -> Unit = {}) {
    Button(onClick = onContinueClicked, modifier) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
        ) {
            Box(
                modifier = modifier
                    .clip(shape = RoundedCornerShape(100.dp))
                    .background(color = MaterialTheme.colorScheme.onPrimary)
            ) {
                Icon(
                    imageVector = androidx.compose.material.icons.Icons.Default.Check,
                    contentDescription = null,
                    modifier = modifier
                        .padding(top = 12.dp, bottom = 12.dp, start = 12.dp, end = 12.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            Column(modifier = modifier.align(Alignment.CenterVertically)) {
                Text(text = "Przejdź do aplikacji")
            }
        }
    }
}

@Preview(name = "Home with overlay")
@Composable
private fun AppPreview() {
    UIProjektZaliczenieTheme {
        AppContent(Modifier.fillMaxSize())
    }
}
