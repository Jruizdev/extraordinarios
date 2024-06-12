package com.example.extraordinarios_jro.utilidades

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.android.volley.toolbox.Volley
import com.example.extraordinarios_jro.Dialogo
import com.example.extraordinarios_jro.RespuestaWebserver
import com.example.extraordinarios_jro.Routes
import org.json.JSONObject

@Composable
fun AgregarRegistro (
    navController: NavController,
    datos: JSONObject,
    dialogo: Dialogo,
    registrar: MutableState<Boolean>
) {
    // Esperar a que se validen los datos
    if (!registrar.value) return

    val volleyRequest = Volley.newRequestQueue (LocalContext.current)
    val resultado = RespuestaWebserver(
        remember { mutableStateOf(arrayOf()) },
        remember { mutableStateOf("") },
        remember { mutableStateOf(false) },
        remember { mutableStateOf(false) }
    )

    // Crear petición para agregar nuevo registro
    val peticion = agregarRegistro(datos, resultado)

    // Realizar petición al webserver
    volleyRequest.add(peticion)

    if(resultado.finalizado.value) {
        if (!resultado.hayError.value) {

            // Registro exitoso
            dialogo.titulo.value = "Registro"
            dialogo.mensaje.value = resultado.resultado.value[0]
            dialogo.onAccept.value = {navController.navigate(Routes.PantallaPrincipal.route)}
            dialogo.mostrar.value = true
        }
        else {
            // Error
            dialogo.titulo.value = "Error"
            dialogo.mensaje.value = resultado.error.value
            dialogo.onAccept.value = {navController.navigate(Routes.PantallaPrincipal.route)}
            dialogo.mostrar.value = true
        }
        registrar.value = false
    }
}