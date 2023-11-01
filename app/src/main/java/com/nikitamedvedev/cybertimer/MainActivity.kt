package com.nikitamedvedev.cybertimer

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nikitamedvedev.cybertimer.ui.theme.CyberTimerTheme
import java.time.LocalDate
import java.time.Month

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
            CyberTimerTheme{
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "home"
                ) {
                    composable("home") {
                        Home(navController)
                    }

                    composable("about-screen") {
                        AboutScreenContent(navController);
                    }
                    composable("edit"){
                        EditScreen(navController)
                    }
                    composable("about-author-screen"){
                        AboutAuthor(navController)
                    }
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AboutAuthor(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.about_author),
                        fontSize = 20.sp)
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigate("about-screen") {
                            }
                        }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.navigation_icon_about)
                        )
                    }
                }
            )

        },
    ) {
        Column() {
            Spacer(
                Modifier.height(70.dp)
            )
            val aboutMeString = MainActivity.aboutMeStrings
            aboutMeString.forEach {
                Text(
                    text = stringResource(id = it),
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun HomeScreenContent(
    items: List<SportEvent>,
    onRemove: (SportEvent) -> Unit,
    onAdd: () -> Unit,
    navController: NavController,
){
    Column() {
        if (items.isEmpty()) {
            EmptyListVariant(onAdd)
        } else {

            items.forEach {
                SportEventItem(
                    event = it,
                    onRemove = {
                        onRemove(it)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 5.dp),
                    navController
                )
            }
        }
    }
}
@Composable
fun EmptyListVariant(onAdd: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "There is no notes",
            color = Color.White,
            fontSize = 36.sp
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = { onAdd() },
            modifier = Modifier.width(100.dp)
        ) {
            Text(text = "Add")
        }
    }
}
@Composable
private fun SportEventItem(
    event: SportEvent,
    onRemove: () -> Unit,
    modifier: Modifier = Modifier,
    navController: NavController
    ) {
    val context = LocalContext.current;
    Card(shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp),
        border = BorderStroke(2.dp, Color.Blue),
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()) {
    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(text = "Sport event: ${event.Name}",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold)
            Text(text = "Date: ${event.Date}",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold)
            Text(text = "Teams: ${event.Teams}",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold)
            Text(text = "Result: ${event.Result}",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold)
            Text(text = "Winner: ${event.Winner}",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold)
        }
        Row(horizontalArrangement = Arrangement.Center) {
            IconButton(
                onClick = {  navController.navigate("edit")}
            ) {
                Icon(
                    Icons.Default.Edit,
                    contentDescription = "Edit",
                )
            }

            IconButton(onClick = {
                onRemove()
                Toast.makeText(context, "You are delete note", Toast.LENGTH_SHORT).show()
            }) {
                Icon(
                    Icons.Default.Delete,
                    contentDescription = stringResource(R.string.home),
                    tint = Color.Red
                )
                }
            }
        }
    }
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navController: NavController) {
    val viewModel = viewModel<HomeViewModel>()
    val context = LocalContext.current;
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                    val event = SportEvent(
                        "Football",
                        LocalDate.of(2023, Month.APRIL, 15),
                        listOf("Barcelona", "Real Madrid"),
                        "2:3",
                        "Real Madrid Win",
                        false
                    )
                    viewModel.items.add(event)
                    Toast.makeText(context, "You are add note", Toast.LENGTH_SHORT).show()
            }){
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        },
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.home),
                        fontSize = 20.sp
                    )},
                navigationIcon = {
                    IconButton(
                        onClick = { navController.navigate("about-screen") {
                            popUpTo("about-screen") }
                        }
                    ) {
                        Icon(
                            Icons.Default.Info,
                            contentDescription = stringResource(id = R.string.navigation_icon_about)
                        )
                    }
                }
            )
                 },
        )
    {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())

            ) {
                Spacer(modifier = Modifier.height(65.dp))
                HomeScreenContent(
                    items = viewModel.items, onRemove = viewModel::onClickRemoveEvent,
                    onAdd = {
                        val event = SportEvent(
                            "Football",
                            LocalDate.of(2023, Month.APRIL, 15),
                            listOf("Barcelona", "Real Madrid"),
                            "2:3",
                            "Real Madrid Win",
                            false
                        )
                        viewModel.items.add(event)
                        Toast.makeText(context, "You are add note", Toast.LENGTH_SHORT).show()
                    },
                    navController
                )
            }
    }
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreenContent(navController: NavController) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(id = R.string.navigation_icon_about),
                            fontSize = 20.sp
                        )
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                navController.navigate("home") {
                                    popUpTo("home") {
                                        inclusive = true
                                    }
                                }
                            }) {
                            Icon(
                                Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = stringResource(id = R.string.navigation_icon_about)
                            )
                        }
                    }
                )

            },
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                print(MainActivity.aboutMeStrings)
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = null,
                    modifier = Modifier
                        .paddingFromBaseline(top = 90.dp)
                        .align(Alignment.CenterHorizontally)
                        .clip(CircleShape)
                        .paint(painter = painterResource(id = R.drawable.ic_launcher_background))
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(R.string.app_name),
                        style = MaterialTheme.typography.headlineMedium,
                    )
                    Text(
                        text = stringResource(id = R.string.app_version),
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(
                    Modifier
                        .height(10.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    val uriHandler = LocalUriHandler.current
                    IconButton(
                        onClick = { },
                        modifier = Modifier
                            .width(80.dp)
                            .height(80.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.instagram_foreground),
                            contentDescription = null,
                            modifier = Modifier
                                .size(65.dp)
                        )
                    }
                    IconButton(
                        onClick = { uriHandler.openUri("https://t.me/+UBErUQ1ZD_wwODcy") },
                        modifier = Modifier
                            .width(80.dp)
                            .height(80.dp)
                    )
                    {
                        Image(
                            painter = painterResource(id = R.drawable.telegram_foreground),
                            contentDescription = null,
                            modifier = Modifier
                                .size(65.dp)
                        )
                    }
                }
                Spacer(
                    Modifier
                        .height(20.dp)
                )
                Column {
                    Row(horizontalArrangement = Arrangement.Start) {
                        TextButton(
                            modifier = Modifier
                                .fillMaxWidth(),
                            onClick = { },
                            shape = RectangleShape
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.privacy_policy_foreground),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(CircleShape)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = stringResource(id = R.string.textbutton_privacy_policy),
                                style = MaterialTheme.typography.headlineSmall,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                            Spacer(modifier = Modifier.width(300.dp))
                        }

                    }
                    Row {
                        val uriHandler = LocalUriHandler.current
                        TextButton(
                            modifier = Modifier
                                .fillMaxWidth(),
                            onClick = { uriHandler.openUri("https://github.com/DrOak1/SportTimer") },
                            shape = RectangleShape,
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.github_foreground),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(CircleShape)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = stringResource(id = R.string.textbutton_github),
                                style = MaterialTheme.typography.headlineSmall,
                                color = MaterialTheme.colorScheme.onBackground,
                            )
                            Spacer(modifier = Modifier.width(300.dp))
                        }
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        TextButton(
                            modifier = Modifier
                                .fillMaxWidth(),
                            onClick = { },
                            shape = RectangleShape
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.bug_report_foreground),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(CircleShape)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = stringResource(id = R.string.textbutton_bug_report),
                                style = MaterialTheme.typography.headlineSmall,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                            Spacer(modifier = Modifier.width(300.dp))
                        }
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        TextButton(
                            modifier = Modifier
                                .fillMaxWidth(),
                            onClick = { },
                            shape = RectangleShape
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.faq_foreground),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(CircleShape)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = stringResource(id = R.string.textbutton_faq),
                                style = MaterialTheme.typography.headlineSmall,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                            Spacer(modifier = Modifier.width(300.dp))
                        }
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        TextButton(
                            modifier = Modifier
                                .fillMaxWidth(),
                            onClick = { navController.navigate("about-author-screen") {
                            } },
                            shape = RectangleShape
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.author_foreground),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(CircleShape)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = "About Author",
                                style = MaterialTheme.typography.headlineSmall,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                            Spacer(modifier = Modifier.width(300.dp))
                        }
                    }
                }
                Spacer(
                    Modifier
                        .height(70.dp)
                )
                Text(
                    text = stringResource(id = R.string.author),
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                )
            }
        }
    }
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
fun EditScreen(
    navController: NavController = rememberNavController(),
    event: SportEvent = SportEvent(
        "Football",
        LocalDate.of(2023, Month.APRIL, 15),
        listOf("Arsenal", "Lokomotiv"),
        "0:0",
        "Match in active",
        true
    )
) {
    Scaffold(
        topBar = { EditTopAppBar(navController) },
    ) {
        EditField(event)
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTopAppBar(navController: NavController) {
    TopAppBar(
        title = {
            Text(
                text = "Edit",
                fontWeight = FontWeight.Bold,
            )
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    navController.navigate("home") {
                        popUpTo("home")
                        {
                            inclusive = true
                        }
                    }
                }) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Navigation Menu",
                    tint = Color.White
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xff1b1b23),
        )
    )
}
@Composable
fun EditField(event: SportEvent) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        Spacer(modifier = Modifier.height(60.dp))
        Field("Sport event name", Icons.Filled.CheckCircle)
        Field("Date", Icons.Filled.Check)
        Field("Teams", Icons.Filled.Check)
        Field("Result", Icons.Filled.Check)
        Field("Winner", Icons.Filled.Check)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Field(name: String, icon: ImageVector, suffix: String = "") {
    var text by remember { mutableStateOf("") }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        OutlinedTextField(
            value = text, onValueChange = { text = it },
            placeholder = {
                Text(
                    text = name,
                    color = Color.White
                )
            },
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                Icon(
                    icon,
                    contentDescription = null,
                    tint = Color.White
                )
            },
            suffix = {
                Text(
                    text = suffix,
                    color = Color.White
                )
            },
            textStyle = androidx.compose.ui.text.TextStyle(color = Color.White),
        )
    }
}
