package com.myth.journi.presentation.screens.pomodoro.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.myth.journi.domain.model.Pomodoro
import com.myth.journi.presentation.screens.task.components.getPercentage
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds
import kotlin.time.DurationUnit


@Composable
fun PomodoroCard(
    pomodoro: Pomodoro
) {
    Card {
        Column(
            modifier = Modifier.padding(all = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Pomodoro(pomodoro)
        }
    }
}

@Composable
fun Pomodoro(settings: Pomodoro) {
    var timeLeft by remember { mutableStateOf(settings.duration) }
    val progress = getPercentage(timeLeft.toInt(), settings.duration.toInt())
    val animationSpec =
        remember { TweenSpec<Float>(durationMillis = 100, easing = FastOutSlowInEasing) }
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = animationSpec,
        label = " "
    )

//    DisposableEffect(Unit) {
//        val timer = fixedRateTimer(period = 1000) {
//            timeLeft -= 1000
//            if (timeLeft <= 0) {
//                cancel()
//            }
//        }
//        onDispose {
//            timer.cancel()
//        }
//    }

    Box(
        modifier = Modifier.padding(16.dp),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator(
            strokeWidth = 16.dp,
            progress = animatedProgress,
            color = MaterialTheme.colorScheme.primary,
        )
        Text(
            text = formatMillis(timeLeft),
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

private fun formatMillis(millis: Long): String {
    val seconds = (millis / 1000) % 60
    val minutes = millis / (1000 * 60)
    return String.format("%02d:%02d", minutes, seconds)
}

@Preview
@Composable
fun PreviewPomodoro() {
    val data = Pomodoro(
        id = 0,
        actionId = 0,
        runs = 2,
        duration = 50.seconds.toLong(DurationUnit.MILLISECONDS),
        shortRestDuration = 5.minutes.toLong(DurationUnit.MILLISECONDS),
        longRestDuration = 15.minutes.toLong(DurationUnit.MILLISECONDS),
        setsBeforeLongRest = 4
    )
    PomodoroCard(pomodoro = data)
}