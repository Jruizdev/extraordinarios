package com.example.extraordinarios_jro.utilidades

import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.example.extraordinarios_jro.RespuestaWebserver

import org.json.JSONObject

// URL del webserver
private val url = "https://extraordinariosjro.x10.mx"

fun carrerasDisponibles (salida: RespuestaWebserver) : JsonArrayRequest {

    val jsonRequest = JsonArrayRequest (
        url + "/?consultar=carreras",
        { elementos ->

            // Almacenar resultados de la petición realizada al webserver
            salida.resultado.value = Array (elementos.length()){
                elementos.getJSONObject(it).get("nombre").toString()
            }
        },
        {
            // Se generó un error de conexión
            salida.error.value = "No fue posible establecer conexión con el servicio."
            salida.hayError.value = true
        }
    )
    return jsonRequest
}

fun agregarRegistro (datos: JSONObject, salida: RespuestaWebserver) : JsonObjectRequest {

    val jsonRequest = JsonObjectRequest (
        Request.Method.POST,
        url,
        datos,
        {
            // Almacenar resultado de la consulta
            salida.resultado.value = arrayOf(it.get("resultado").toString())
            salida.finalizado.value = true
        },
        {
            // Error al realizar la petición
            salida.error.value = it.message.toString()
            salida.hayError.value = true
            salida.finalizado.value = true
        }
    )
    return jsonRequest
}