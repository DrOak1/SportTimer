package com.nikitamedvedev.cybertimer

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nikitamedvedev.cybertimer.ui.theme.CyberTimerTheme


class MainActivity : ComponentActivity() {
    companion object {
        val aboutMeStrings = listOf(
            R.string.first_fact,
            R.string.second_fact,
            R.string.third_fact
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CyberTimerTheme {
                AboutScreen()
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AboutScreen() {
    Scaffold(
        modifier = Modifier.verticalScroll(rememberScrollState()),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "About",
                        fontSize = 20.sp
                    )
                },
                colors = topAppBarColors(
        containerColor = MaterialTheme.colorScheme.background
        ),
                navigationIcon = {
                    IconButton(
                        onClick = {
                        }
                    ) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "About"
                        )
                    }
                }
            )

        },
        content = {
            Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null,
                modifier = Modifier
                    .paddingFromBaseline(top = 90.dp)
                    .align(Alignment.CenterHorizontally)
                    .clip(CircleShape)
                    .paint(painter = painterResource(id = R.drawable.ic_launcher_background))
            )
                Column(modifier = Modifier
                    .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally)
                {
                    Text(text = "CyberTimer",
                        style = MaterialTheme.typography.headlineMedium,)
                    Text(text = "App Version 0.0.1",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold)}
                Spacer(Modifier
                    .height(10.dp))
            Row(modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
                ){
                val uriHandler = LocalUriHandler.current
                IconButton(onClick = {
                    },
                    modifier = Modifier
                        .width(80.dp)
                        .height(80.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.instagram_foreground),
                        contentDescription = null,
                        modifier = Modifier
                            .size(65.dp))
                }
                IconButton(onClick = {  uriHandler.openUri("https://t.me/+UBErUQ1ZD_wwODcy") },
                    modifier = Modifier
                        .width(80.dp)
                        .height(80.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.telegram_foreground),
                        contentDescription = null,
                        modifier = Modifier
                            .size(65.dp))
                }
            }
                Spacer(Modifier
                    .height(20.dp))
            Column() {
                Row(horizontalArrangement = Arrangement.Start) {
                    TextButton(
                        modifier= Modifier
                            .fillMaxWidth(),
                        onClick = { },
                        shape = RectangleShape
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.privacy_policy_foreground),
                            contentDescription = null,
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape))
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "Privacy Policy",
                            style = MaterialTheme.typography.headlineSmall,
                            color = MaterialTheme.colorScheme.onBackground)
                        Spacer(modifier = Modifier.width(300.dp))
                    }

                }
                Row(){
                    TextButton(
                        modifier= Modifier
                            .fillMaxWidth(),
                        onClick = {  },
                        shape = RectangleShape,
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.github_foreground),
                            contentDescription = null,
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape))
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "GitHub",
                            style = MaterialTheme.typography.headlineSmall,
                            color = MaterialTheme.colorScheme.onBackground,)
                        Spacer(modifier = Modifier.width(300.dp))
                    }
                }
                Row(verticalAlignment = Alignment.CenterVertically){
                    TextButton(
                        modifier= Modifier
                            .fillMaxWidth(),
                        onClick = {  },
                        shape = RectangleShape
                        ) {
                        Image(
                            painter = painterResource(id = R.drawable.bug_report_foreground),
                            contentDescription = null,
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape))
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "Bug report",
                            style = MaterialTheme.typography.headlineSmall,
                            color = MaterialTheme.colorScheme.onBackground)
                        Spacer(modifier = Modifier.width(300.dp))
                    }
                }
                Row(verticalAlignment = Alignment.CenterVertically){
                    TextButton(
                        modifier= Modifier
                            .fillMaxWidth(),
                        onClick = {  },
                        shape = RectangleShape
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.faq_foreground),
                            contentDescription = null,
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape))
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "FAQ",
                            style = MaterialTheme.typography.headlineSmall,
                            color = MaterialTheme.colorScheme.onBackground)
                        Spacer(modifier = Modifier.width(300.dp))
                    }
                }
            }
                Spacer(Modifier
                    .height(120.dp))
            Text(text = "Author: Nikita Medvedev",
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally))
            }
        }
    )
}


