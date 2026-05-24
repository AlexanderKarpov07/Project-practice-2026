package ru.mospolytech.lkapp.ui.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ru.mospolytech.lkapp.core.presentation.main.PhysicalScreen

@Composable
fun HomeScreen() {

    var selectedTab by remember { mutableStateOf(3) }
    var openPhys by remember { mutableStateOf(false) }
    var journalOpened by remember { mutableStateOf(false) }

    Scaffold(

        contentWindowInsets = WindowInsets(0.dp),

        bottomBar = {
            NavigationBar(
                containerColor = Color(0xFF1E1E1E)
            ) {

                NavigationBarItem(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    icon = {
                        Icon(
                            Icons.Default.Article,
                            contentDescription = "",
                            tint = Color(0xFFEDE1C9)
                        )
                    },
                    label = { Text("Новости") }
                )

                NavigationBarItem(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    icon = {
                        Icon(
                            Icons.Default.DateRange,
                            contentDescription = "",
                            tint = Color(0xFFEDE1C9)
                        )
                    },
                    label = { Text("Расписание") }
                )

                NavigationBarItem(
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 },
                    icon = {
                        Icon(
                            Icons.Default.ChatBubbleOutline,
                            contentDescription = "",
                            tint = Color(0xFFEDE1C9)
                        )
                    },
                    label = { Text("Чаты") }
                )

                NavigationBarItem(
                    selected = selectedTab == 3,
                    onClick = { selectedTab = 3 },
                    icon = {
                        Icon(
                            Icons.Default.GridView,
                            contentDescription = "",
                            tint = Color(0xFFE5C07B)
                        )
                    },
                    label = { Text("Сервисы") }
                )
            }
        },

        containerColor = Color(0xFF121212)

    ) { padding ->

        when (selectedTab) {

            //НОВОСТИ
            0 -> LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        bottom = padding.calculateBottomPadding()
                    )
                    .padding(16.dp)
            ) {

                items(5) {

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFF1E1E1E)
                        )
                    ) {

                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {

                            Text(
                                text = "Новость ${it + 1}",
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(modifier = Modifier.height(6.dp))

                            Text(
                                text = "Описание новости и дополнительная информация",
                                color = Color.Gray
                            )
                        }
                    }
                }
            }

            //РАСПИСАНИЕ
            1 -> LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        bottom = padding.calculateBottomPadding()
                    )
                    .padding(16.dp)
            ) {

                items(5) {

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFF1E1E1E)
                        )
                    ) {

                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {

                            Text(
                                text = "Пара ${it + 1}",
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(modifier = Modifier.height(6.dp))

                            Text(
                                text = "09:00 - 10:30 • Аудитория 301",
                                color = Color.Gray
                            )
                        }
                    }
                }
            }

            //ЧАТЫ
            2 -> LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        bottom = padding.calculateBottomPadding()
                    )
                    .padding(16.dp)
            ) {

                items(5) {

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFF1E1E1E)
                        )
                    ) {

                        Row(
                            modifier = Modifier.padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Box(
                                modifier = Modifier
                                    .size(52.dp)
                                    .background(
                                        Color(0xFF2A241A),
                                        shape = MaterialTheme.shapes.large
                                    ),
                                contentAlignment = Alignment.Center
                            ) {

                                Icon(
                                    Icons.Default.Person,
                                    contentDescription = "",
                                    tint = Color(0xFFEDE1C9)
                                )
                            }

                            Spacer(modifier = Modifier.width(16.dp))

                            Column {

                                Text(
                                    text = "Чат ${it + 1}",
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold
                                )

                                Spacer(modifier = Modifier.height(4.dp))

                                Text(
                                    text = "Последнее сообщение...",
                                    color = Color.Gray
                                )
                            }
                        }
                    }
                }
            }

            //СЕРВИСЫ
            3 -> {

                if (openPhys) {

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xFF121212))
                    ) {

                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {

                            // HEADER
                            if (!journalOpened) {

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(
                                            start = 12.dp,
                                            end = 12.dp,
                                            top = 32.dp,
                                            bottom = 8.dp
                                        ),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {

                                    IconButton(
                                        onClick = {
                                            openPhys = false
                                        }
                                    ) {
                                        Icon(
                                            Icons.Default.ArrowBack,
                                            contentDescription = null,
                                            tint = Color.White
                                        )
                                    }

                                    Text(
                                        text = "Физическая культура",
                                        color = Color.White,
                                        style = MaterialTheme.typography.titleLarge
                                    )
                                }
                            }

                            // SCREEN
                            Box(
                                modifier = Modifier.weight(1f)
                            ) {
                                PhysicalScreen(
                                    onJournalStateChange = {
                                        journalOpened = it
                                    }
                                )
                            }
                        }

                        // BOTTOM NAVBAR
                        NavigationBar(
                            modifier = Modifier.align(Alignment.BottomCenter),
                            containerColor = Color(0xFF1E1E1E)
                        ) {

                            NavigationBarItem(
                                selected = false,
                                onClick = {
                                    openPhys = false
                                    selectedTab = 0
                                },
                                icon = {
                                    Icon(Icons.Default.Article, null)
                                },
                                label = { Text("Новости") }
                            )

                            NavigationBarItem(
                                selected = false,
                                onClick = {
                                    openPhys = false
                                    selectedTab = 1
                                },
                                icon = {
                                    Icon(Icons.Default.DateRange, null)
                                },
                                label = { Text("Расписание") }
                            )

                            NavigationBarItem(
                                selected = false,
                                onClick = {
                                    openPhys = false
                                    selectedTab = 2
                                },
                                icon = {
                                    Icon(Icons.Default.ChatBubbleOutline, null)
                                },
                                label = { Text("Чаты") }
                            )

                            NavigationBarItem(
                                selected = true,
                                onClick = { },
                                icon = {
                                    Icon(Icons.Default.GridView, null)
                                },
                                label = { Text("Сервисы") }
                            )
                        }
                    }
                } else {

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(
                                bottom = padding.calculateBottomPadding()
                            )
                            .padding(16.dp)
                    ) {

                        item {

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {

                                Text(
                                    "Сервисы",
                                    color = Color(0xFFEDE1C9),
                                    style = MaterialTheme.typography.titleLarge
                                )

                                Icon(
                                    Icons.Default.Settings,
                                    contentDescription = "",
                                    tint = Color(0xFFEDE1C9)
                                )
                            }

                            Spacer(modifier = Modifier.height(24.dp))

                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {

                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {

                                    Box(
                                        modifier = Modifier
                                            .size(120.dp)
                                            .background(
                                                Color(0xFF2A241A),
                                                shape = MaterialTheme.shapes.extraLarge
                                            ),
                                        contentAlignment = Alignment.Center
                                    ) {

                                        Icon(
                                            Icons.Default.Person,
                                            contentDescription = "",
                                            tint = Color(0xFFEDE1C9),
                                            modifier = Modifier.size(48.dp)
                                        )
                                    }

                                    Spacer(modifier = Modifier.height(16.dp))

                                    Text(
                                        "Александр Карпов",
                                        color = Color(0xFFEDE1C9)
                                    )

                                    Text(
                                        "251-371",
                                        color = Color.Gray
                                    )

                                    Spacer(modifier = Modifier.height(16.dp))

                                    Button(
                                        onClick = {},
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = Color(0xFFE5C07B)
                                        )
                                    ) {

                                        Text(
                                            "Подробнее",
                                            color = Color.Black
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(32.dp))
                        }

                        fun serviceItem(
                            title: String,
                            subtitle: String,
                            color: Color,
                            icon: ImageVector,
                            onClick: () -> Unit = {}
                        ) {

                            item {

                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 8.dp)
                                        .clickable {
                                            onClick()
                                        },
                                    colors = CardDefaults.cardColors(
                                        containerColor = Color(0xFF1E1E1E)
                                    )
                                ) {

                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(16.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {

                                        Box(
                                            modifier = Modifier
                                                .size(48.dp)
                                                .background(
                                                    color,
                                                    shape = MaterialTheme.shapes.medium
                                                ),
                                            contentAlignment = Alignment.Center
                                        ) {

                                            Icon(
                                                icon,
                                                contentDescription = "",
                                                tint = Color.Black
                                            )
                                        }

                                        Spacer(modifier = Modifier.width(16.dp))

                                        Column {

                                            Text(
                                                title,
                                                color = Color(0xFFEDE1C9),
                                                fontWeight = FontWeight.Medium
                                            )

                                            Text(
                                                subtitle,
                                                color = Color.Gray
                                            )
                                        }
                                    }
                                }
                            }
                        }

                        serviceItem(
                            "Результаты сессии",
                            "Проверить успеваемость",
                            Color(0xFF8EC5FC),
                            Icons.Default.School
                        )

                        serviceItem(
                            "Проектная деятельность",
                            "Информация о проектах",
                            Color(0xFFD7C4F2),
                            Icons.Default.Lightbulb
                        )

                        serviceItem(
                            "Цифровые сервисы",
                            "Сервис подачи заявок",
                            Color(0xFFCCD6F6),
                            Icons.Default.Description
                        )

                        serviceItem(
                            "Физическая культура",
                            "Посещаемость и Оценка",
                            Color(0xFFF5C6D6),
                            Icons.Default.FitnessCenter
                        ) {
                            openPhys = true
                        }
                    }
                }
            }
        }
    }
}