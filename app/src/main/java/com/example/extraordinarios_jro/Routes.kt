package com.example.extraordinarios_jro

sealed class Routes (val route: String) {
    object PantallaPrincipal: Routes ("principal")
    object PantallaRegistro: Routes ("registro")
}