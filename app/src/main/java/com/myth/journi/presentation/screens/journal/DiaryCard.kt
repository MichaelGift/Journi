package com.myth.journi.presentation.screens.journal

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.myth.journi.ui.theme.JourniTheme

@Composable
fun DiaryCard(
    onClick: () -> Unit,
    mood: String,
    title: String,
    description: String,
    date: Int,
    day: String
) {
    ElevatedCard(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .clickable { onClick }
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "$date", fontWeight = FontWeight.SemiBold, fontSize = 20.sp)

                Text(text = day)
            }

            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(text = mood)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = title, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                }

                Spacer(modifier = Modifier.padding(4.dp))
                Text(text = description)
            }
        }
    }
}


@Preview
@Composable
fun PreviewDiaryCard() {
    JourniTheme {

        DiaryCard(
            {}, "\uD83D\uDE00","Sample Diary Card", "A lot of random junk here",2,"Mon"
        )
    }
}