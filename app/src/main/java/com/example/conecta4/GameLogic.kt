package com.example.conecta4
// Representa el tablero del juego, guardando la lista de movimientos realizados.
class Board(val moves: List<Move> = emptyList()) {
 // Devuelve el jugador que tiene el turno actual.
    fun currentPlayer(): Player =
        if (moves.isEmpty()) Player.Jugador else moves.last().player.opponent()
  // Intenta colocar una ficha en la columna indicada para el jugador.
    fun place(column: BoardColumn, player: Player): Board {
        if (moves.count { it.column == column } >= BoardRow.values().size) {
            throw IllegalArgumentException("La columna $column está llena.")
        }
        // Devuelve un nuevo tablero con el movimiento agregado.
        return Board(moves + Move(player, column))
    }
   // Devuelve qué jugador ocupa una celda específica del tablero.
    fun getCell(row:BoardRow, column: BoardColumn): Player? {
        val pile = moves.filter { it.column == column }
        return if (pile.size > row.ordinal) pile[row.ordinal].player else null
    }

    fun checkVictory(): Player? {
        // Crea una grilla vacía
        val grid = Array(6) { Array<Player?>(7) { null } }

        // Rellena el grid simulando cómo se apilan las fichas en cada columna
        val columnHeights = IntArray(7) { 0 } // para llevar el conteo por columna

        for (move in moves) {
            val col = move.column.ordinal
            val row = columnHeights[col]
            if (row < 6) {
                grid[row][col] = move.player
                columnHeights[col]++
            }
        }

        // Verificar en las 4 direcciones
        fun checkDirection(dx: Int, dy: Int): Player? {
            for (r in 0 until 6) {
                for (c in 0 until 7) {
                    val player = grid[r][c] ?: continue
                    var count = 1
                    var x = c + dx
                    var y = r + dy
                    while (x in 0..6 && y in 0..5 && grid[y][x] == player) {
                        count++
                        if (count == 4) return player
                        x += dx
                        y += dy
                    }
                }
            }
            return null
        }

        // Revisa horizontal, vertical y diagonales
        return checkDirection(1, 0) ?: // horizontal →
        checkDirection(0, 1) ?: // vertical ↓
        checkDirection(1, 1) ?: // diagonal ↘
        checkDirection(1, -1)   // diagonal ↗
    }



}
