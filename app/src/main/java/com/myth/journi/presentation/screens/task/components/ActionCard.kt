package com.myth.journi.presentation.screens.task.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.myth.journi.domain.model.Action

@Composable
fun ActionStats(
    item: Action,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(all = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    TaskTimeStart(item.timeBlockStart.toString())
                    TaskTimeEnd(item.timeBlockEnd.toString())
                }
                Spacer(modifier = Modifier.width(8.dp))
                Separator()
                Spacer(modifier = Modifier.width(8.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        TaskCategoryIcon()
                        Spacer(modifier = Modifier.width(4.dp))
                        TaskCategoryTitle(item.category)
                        Spacer(modifier = Modifier.width(4.dp))
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        TaskName(item.title)
                    }
                }

                Spacer(modifier = Modifier.width(8.dp))
                Separator()
                Spacer(modifier = Modifier.width(8.dp))

                TaskProgressIndicator(item.completed, item.total)
            }
        }
    }
}

@Composable
fun TaskDueDate(date: String) {
    Text(text = date, style = MaterialTheme.typography.bodySmall)
}

@Composable
fun Separator() {
    Box(
        modifier = Modifier
            .height(40.dp)
            .width(2.dp)
            .background(Color.Gray)
    )
}

@Composable
fun TaskName(name: String) {
    Text(text = name, style = MaterialTheme.typography.titleMedium)
}

@Composable
fun TaskProgressIndicator(completed: Int, total: Int) {
    val progress = getPercentage(completed, total)
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec,
        label = ""
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(40.dp)
    ) {
        CircularProgressIndicator(
            progress = animatedProgress,
            color = MaterialTheme.colorScheme.primary,
            trackColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),
            modifier = Modifier.size(40.dp)
        )
        Text(
            text = "${(progress * 100).toInt()}%",
            style = MaterialTheme.typography.bodySmall.copy(
                fontWeight = FontWeight.Bold
            )
        )
    }
}

fun getPercentage(completed: Int, total: Int): Float {
    return (completed.toFloat() / total.toFloat())
}

@Composable
fun TaskLevel(current: Int, max: Int) {
    Text(text = "Lv $current / $max", style = MaterialTheme.typography.bodySmall)
}

@Composable
fun TaskCategoryTitle(category: String) {
    Text(text = category, style = MaterialTheme.typography.bodySmall)
}

@Composable
fun TaskCategoryIcon() {
    Icon(
        imageVector = Icons.Default.Category,
        contentDescription = "Task Icon",
        modifier = Modifier.size(16.dp)
    )
}

@Composable
fun TaskTimeEnd(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodySmall
    )
}

@Composable
fun TaskTimeStart(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium.copy(
            fontWeight = FontWeight.Bold
        ),
        modifier = Modifier.padding(
            bottom = 4.dp
        )
    )
}