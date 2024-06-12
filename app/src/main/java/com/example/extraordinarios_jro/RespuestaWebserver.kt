package com.example.extraordinarios_jro

import androidx.compose.runtime.MutableState

data class RespuestaWebserver (
    var resultado: MutableState<Array<String>>,
    var error: MutableState<String>,
    var hayError: MutableState<Boolean>,
    var finalizado: MutableState<Boolean>
) {
    override fun equals (other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RespuestaWebserver

        if (!resultado.value.contentEquals(other.resultado.value)) return false
        if (error != other.error) return false

        return true
    }

    override fun hashCode (): Int {
        var result = resultado.value.contentHashCode()
        result = 31 * result + error.hashCode()
        return result
    }
}
