package com.example.conecta4

enum class BoardColumn { a, b, c, d, e, f, g }
enum class BoardRow { r1, r2, r3, r4, r5, r6 }
enum class Player(val sign: Char) {
    Maquina('X'), Jugador('O');
    fun opponent() = if (this == Maquina) Jugador else Maquina
}

data class Move(val player: Player, val column: BoardColumn)