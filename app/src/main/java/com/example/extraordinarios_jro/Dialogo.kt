package com.example.extraordinarios_jro

import androidx.compose.runtime.MutableState

data class Dialogo(
    var titulo: MutableState<String>,
    var mensaje: MutableState<String>,
    var mostrar: MutableState<Boolean>,
    var onAccept: MutableState<() -> Unit>
)
