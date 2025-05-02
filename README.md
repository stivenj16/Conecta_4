# Conecta 4 (Android - Jetpack Compose)

Este es un proyecto de ejemplo del clásico juego **Conecta 4**, desarrollado en Kotlin para Android usando Jetpack Compose. El jugador humano compite contra la máquina en turnos alternados.

#Funcionalidades

- Tablero de 6 filas por 7 columnas.
- Turnos automáticos entre Jugador y Máquina.
- Lógica completa para detectar el ganador (horizontal, vertical y diagonales).
- Interfaz visual con fichas rojas (Máquina) y amarillas (Jugador).
- Validación de columna llena.
- Mensaje de ganador al final de la partida.

# Tecnologías

- Kotlin
- Jetpack Compose
- Android Studio

#▶️ Cómo ejecutar

1. Clona este repositorio.
2. Abre el proyecto en Android Studio.
3. Ejecuta la aplicación en un emulador o dispositivo físico.

#Estructura del proyecto

- `MainActivity.kt`: Lanza la interfaz principal.
- `Board.kt`: Contiene la lógica del juego (movimientos, turnos, verificación de victoria).
- `Conecta4Game.kt`: Composable que construye la UI del tablero y gestiona la interacción.
- `Player.kt`: Define los jugadores, las filas y columnas del tablero.
