package ru.mospolytech.lkapp.core.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Student(
    val name: String,
    val visits: Int,
    val points: Int,
    val lms: Int,
    val group: String
)

@Composable
fun GroupJournalScreen(
    onBack: () -> Unit
) {

    val students = listOf(
        Student("Иванов Иван Иванович", 20, 50, 0, "Подготовительная"),
        Student("Петров Петр Сергеевич", 20, 50, 0, "Подготовительная"),
        Student("Сидоров Алексей Олегович", 20, 50, 0, "Подготовительная"),
        Student("Кузнецов Дмитрий Игоревич", 20, 50, 0, "Подготовительная"),
        Student("Смирнов Максим Андреевич", 20, 50, 0, "Подготовительная"),
        Student("Васильев Артем Денисович", 20, 50, 0, "Подготовительная"),
        Student("Попов Никита Романович", 20, 50, 0, "Подготовительная"),
        Student("Федоров Кирилл Павлович", 20, 50, 0, "Подготовительная"),
        Student("Морозов Владислав Ильич", 20, 50, 0, "Подготовительная"),
        Student("Новиков Егор Максимович", 20, 50, 0, "Подготовительная")
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {

            item {

                Spacer(modifier = Modifier.height(12.dp))

                IconButton(
                    onClick = onBack
                ) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = null,
                        tint = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Журнал группы",
                    color = Color.White,
                    fontSize = 34.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(30.dp))

                Text(
                    text = "Всего студентов: 35",
                    color = Color(0xFFE0D6C8),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            items(students) { student ->

                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF1E1A12)
                    ),
                    shape = RoundedCornerShape(28.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Box(
                                modifier = Modifier
                                    .size(56.dp)
                                    .background(
                                        Color(0xFF3A2B18),
                                        RoundedCornerShape(18.dp)
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = student.name.first().toString(),
                                    color = Color(0xFFD4B06A),
                                    fontSize = 28.sp
                                )
                            }

                            Spacer(modifier = Modifier.width(18.dp))

                            Text(
                                text = student.name,
                                color = Color.White,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    Color(0xFF3A3227),
                                    RoundedCornerShape(22.dp)
                                )
                                .padding(vertical = 22.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {

                            StatBlock("20", "Посещения")
                            StatBlock("50", "Баллы")
                            StatBlock("0", "LMS")
                        }

                        Spacer(modifier = Modifier.height(18.dp))

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Icon(
                                Icons.Default.School,
                                contentDescription = null,
                                tint = Color.White
                            )

                            Spacer(modifier = Modifier.width(10.dp))

                            Text(
                                text = student.group,
                                color = Color.White,
                                fontSize = 18.sp
                            )
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(120.dp))
            }
        }
    }
}

@Composable
fun StatBlock(
    value: String,
    title: String
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = value,
            color = Color(0xFFD4B06A),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = title,
            color = Color.White,
            fontSize = 15.sp
        )
    }
}