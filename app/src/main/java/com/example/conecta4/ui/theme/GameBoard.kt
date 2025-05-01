package com.example.conecta4.ui.theme

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.conecta4.*

@Composable
fun Conecta4Game() {
    var board by remember { mutableStateOf(Board()) }
    var winner by remember { mutableStateOf<Player?>(null) }
    var isPlayerTurn by remember { mutableStateOf(true) }
    var isDraw by remember { mutableStateOf(false) }
    var navigateToVictory by remember { mutableStateOf(false) }
    val context = LocalContext.current

    // Función para que la máquina haga un movimiento aleatorio
    fun machineMove() {
        if (winner == null) {
            val availableColumns = BoardColumn.values().filter {
                board.moves.count { move -> move.column == it } < BoardRow.values().size
            }
            if (availableColumns.isNotEmpty()) {
                val randomColumn = availableColumns.random()
                board = board.place(randomColumn, Player.Maquina)
                winner = board.checkVictory()
            }
        }
    }

    // Lógica para alternar entre jugador y máquina
    LaunchedEffect(board) {
        if (!isPlayerTurn && winner == null) {
            machineMove()
            isPlayerTurn = true
        }
    }

    // Función para verificar y mostrar el ganador
    fun checkForWinner() {
        winner = board.checkVictory()
    }

    // Llamar a la verificación de ganador cada vez que haya un movimiento
    LaunchedEffect(board) {
        checkForWinner()
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            "Turno de: ${if (isPlayerTurn) "Jugador" else "Máquina"}",
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(16.dp))

        for (row in BoardRow.values().reversed()) {
            Row {
                for (col in BoardColumn.values()) {
                    val chip = board.getCell(row, col)
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .padding(4.dp)
                            .clickable(enabled = winner == null && isPlayerTurn) {
                                try {
                                    board = board.place(col, Player.Jugador) //
                                    isPlayerTurn = false
                                } catch (_: Exception) { }
                            }
                            .background(
                                when (chip) {
                                    Player.Maquina -> Color.Red
                                    Player.Jugador -> Color.Yellow
                                    null -> Color.LightGray
                                },
                                CircleShape
                            )
                    )
                }
            }
        }

        when {
            winner != null -> {
                navigateToVictory = true
            }
            isDraw -> {
                Spacer(modifier = Modifier.height(16.dp))
                Text("¡Empate!", fontSize = 24.sp, color = Color.Blue)
            }
        }
    }

    // Aquí lanzamos la VictoryActivity cuando hay un ganador
    LaunchedEffect(navigateToVictory) {
        if (navigateToVictory && winner != null) {
            val winnerName = winner?.name ?: "Desconocido"
            val intent = Intent(context, VictoryActivity::class.java)
            intent.putExtra("WINNER", winnerName)
            context.startActivity(intent)
        }
    }
}


