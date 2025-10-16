package wsb.studia.mobile_ui_5sem

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.FontScaling
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import wsb.studia.mobile_ui_5sem.ui.theme.UIProjektZaliczenieTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UIProjektZaliczenieTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    var shouldShowWelcome by rememberSaveable { mutableStateOf(true) }

    Surface(modifier) {
        if (shouldShowWelcome) {
            WelcomeContent(onContinueClicked = { shouldShowWelcome = false })
        } else {
            //Greetings()
        }
    }
}

@Composable
fun WelcomeContent(
    modifier: Modifier = Modifier,
    onContinueClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(
                color = Color(0xFFFFFFFF),
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .background(
                    color = Color(0xFFFFFFFF),
                )
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
                    Text("Aplikacja Nutri-Spy jest w fazie testów. Nie jest publicznie dostępna. Wszelkie problemy proszę zgłaszać do\n Grzegorza Widery i Szymona Gorczycy",
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
fun ConfirmationButton(modifier: Modifier = Modifier, onContinueClicked: () -> Unit = {}) {
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

@Preview
@Composable
private fun WelcomeContentPreview() {
    WelcomeContent(onContinueClicked = {})
}

@Preview
@Composable
private fun ConfirmationButtonPreview() {
    ConfirmationButton()
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    UIProjektZaliczenieTheme {
        MainScreen(Modifier.fillMaxSize())
    }
}
