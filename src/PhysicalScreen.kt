package ru.mospolytech.lkapp.core.presentation.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Article

// ================= COLORS =================

val Bg = Color(0xFF121212)

val CardLight = Color(0xFF2A2A2A)
val CardDark = Color(0xFF1E1E1E)
val Green = Color(0xFF6BE675)
val GreenSoft = Color(0xFF2E3B1F)
val Gold = Color(0xFFE0B96D)

val TextMain = Color.White
val TextSecondary = Color(0xFF9E9E9E)

// ================= DATA =================

data class Visit(
    val teacher: String,
    val date: String,
    val status: String
)

// ================= MAIN =================

@Composable
fun PhysicalScreen(
    onJournalStateChange: (Boolean) -> Unit = {}
) {

    val visits = listOf(
        Visit("Шилова Марина Викторовна", "03/14/2026", "ok"),
        Visit("Шилова Марина Викторовна", "03/10/2026", "ok")
    )

    var selectedTab by remember { mutableStateOf(0) }
    var openJournal by remember { mutableStateOf(false) }

    // Открытие журнала группы
    if (openJournal) {

        GroupJournalScreen(
            onBack = {
                openJournal = false
                onJournalStateChange(false)
            }
        )

        return
    }

    Surface(color = Bg) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 100.dp
                ),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            item { HeaderBlock() }

            item {
                JournalCard(
                    onClick = {
                        openJournal = true
                        onJournalStateChange(true)
                    }
                )
            }

            item {
                Tabs(selectedTab) {
                    selectedTab = it
                }
            }

            when (selectedTab) {

                0 -> {
                    items(visits) {
                        VisitCard(it)
                    }
                }

                1 -> item {
                    Placeholder("Нормативы пока отсутствуют")
                }

                2 -> item {
                    Placeholder("Доп. баллы пока отсутствуют")
                }
            }
        }
    }
}

// ================= HEADER =================

@Composable
fun HeaderBlock() {

    Card(
        colors = CardDefaults.cardColors(containerColor = CardDark),
        shape = RoundedCornerShape(26.dp)
    ) {

        Column(modifier = Modifier.padding(20.dp)) {

            Text(
                "Физическая культура",
                color = TextSecondary,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {

                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .background(Green, RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(22.dp)
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column {

                    Text(
                        "Карпов Александр Владимирович",
                        color = TextMain,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        "251-371",
                        color = TextSecondary,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Column {

                    Text(
                        "КУРС",
                        color = TextSecondary,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        "1",
                        color = TextMain,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Column {

                    Text(
                        "СПЕЦ.",
                        color = TextSecondary,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        "GeneralPhysicalTraining Gym",
                        color = TextMain,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Divider(color = Color.Gray.copy(alpha = 0.3f))

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                "КУРАТОР",
                color = TextSecondary,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                "Щербин Дмитрий Владимирович",
                color = TextMain,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                "ГРУППА ЗДОРОВЬЯ",
                color = TextSecondary,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                "Подготовительная",
                color = TextMain,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {

                InfoBox(
                    "ЛМС",
                    "0",
                    CardLight,
                    Modifier.weight(1f)
                )

                InfoBox(
                    "ВСЕГО",
                    "7",
                    Gold,
                    Modifier.weight(1f),
                    textColor = Color.Black
                )
            }
        }
    }
}

// ================= INFO BOX =================

@Composable
fun InfoBox(
    title: String,
    value: String,
    color: Color,
    modifier: Modifier,
    textColor: Color = Color.White
) {

    Box(
        modifier = modifier
            .background(color, RoundedCornerShape(16.dp))
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Text(
                title,
                color = textColor.copy(alpha = 0.7f),
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                value,
                color = textColor,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

// ================= JOURNAL =================

@Composable
fun JournalCard(
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        colors = CardDefaults.cardColors(containerColor = CardDark),
        shape = RoundedCornerShape(22.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(46.dp)
                    .background(Color(0xFF4A2E2E), RoundedCornerShape(14.dp)),
                contentAlignment = Alignment.Center
            ) {

                Icon(
                    imageVector = Icons.Default.Article,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(22.dp)
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column {

                Text(
                    "Журнал группы",
                    color = TextMain,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    "Рейтинг твоей группы",
                    color = TextSecondary,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

// ================= TABS =================

@Composable
fun Tabs(selected: Int, onSelect: (Int) -> Unit) {

    val titles = listOf(
        "Посещения",
        "Нормативы",
        "Доп. баллы"
    )

    Card(
        shape = RoundedCornerShape(30.dp),
        colors = CardDefaults.cardColors(containerColor = CardDark)
    ) {

        Row(modifier = Modifier.padding(6.dp)) {

            titles.forEachIndexed { index, title ->

                val active = selected == index

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .background(
                            if (active) GreenSoft else Color.Transparent,
                            RoundedCornerShape(20.dp)
                        )
                        .padding(vertical = 10.dp)
                        .clickable {
                            onSelect(index)
                        },
                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        title,
                        color = if (active) Green else TextSecondary,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

// ================= VISIT =================

@Composable
fun VisitCard(item: Visit) {

    Card(
        colors = CardDefaults.cardColors(containerColor = CardDark),
        shape = RoundedCornerShape(20.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(44.dp)
                    .background(Green, RoundedCornerShape(14.dp)),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    "✓",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column {

                Text(
                    "Посещение",
                    color = TextMain,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    item.teacher,
                    color = TextSecondary,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    "Дата: ${item.date}",
                    color = TextSecondary,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

// ================= PLACEHOLDER =================

@Composable
fun Placeholder(text: String) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(40.dp),
        contentAlignment = Alignment.Center
    ) {

        Text(
            text,
            color = TextSecondary,
            fontWeight = FontWeight.Bold
        )
    }
}