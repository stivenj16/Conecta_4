package com.example.conecta4

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class VictoryActivity : ComponentActivity() { // Usa ComponentActivity para Compose
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val winner = intent.getStringExtra("WINNER")

        setContent {
            VictoryScreen(winner)
        }
    }
}

@Composable
fun VictoryScreen(winner: String?) {
    val context = LocalContext.current

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "¡Ganador!",
                fontSize = 32.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = winner ?: "Desconocido",
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = {
                // Lanzar MainActivity
                context.startActivity(
                    Intent(context, MainActivity::class.java)
                )
                // Cerrar VictoryActivity
                if (context is ComponentActivity) {
                    context.finish()
                }
            }) {
                Text("Jugar de nuevo")
            }
        }
    }
}



