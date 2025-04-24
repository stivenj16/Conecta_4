package com.example.conecta4

class Board(val moves: List<Move> = emptyList()) {

    fun currentPlayer(): Player =
        if (moves.isEmpty()) Player.Jugador else moves.last().player.opponent()

    fun place(column: BoardColumn): Board {
        if (moves.count { it.column == column } >= BoardRow.values().size) {
            throw IllegalArgumentException("La columna $column está llena.")
        }
        return Board(moves + Move(currentPlayer(), column))
    }

    fun getCell(row:BoardRow, column: BoardColumn): Player? {
        val pile = moves.filter { it.column == column }
        return if (pile.size > row.ordinal) pile[row.ordinal].player else null
    }

    fun checkVictory(): Player? {
        val grid = Array(6) { Array<Player?>(7) { null } }

        moves.forEach { move ->
            val row = moves.filter { it.column == move.column }.indexOf(move)
            grid[row][move.column.ordinal] = move.player
        }

        // Función para verificar en todas las direcciones posibles
        fun checkDirection(dx: Int, dy: Int): Player? {
            for (r in 0 until 6) {
                for (c in 0 until 7) {
                    val player = grid[r][c] ?: continue
                    var count = 1
                    var x = c + dx
                    var y = r + dy
                    while (x in 0..6 && y in 0..5 && grid.getOrNull(y)?.getOrNull(x) == player) {
                        count++
                        if (count == 4) return player
                        x += dx
                        y += dy
                    }
                }
            }
            return null
        }

        return checkDirection(1, 0) ?: checkDirection(0, 1) ?: checkDirection(1, 1) ?: checkDirection(1, -1)
    }


}