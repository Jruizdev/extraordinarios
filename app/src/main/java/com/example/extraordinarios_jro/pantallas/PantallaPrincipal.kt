package com.example.extraordinarios_jro.pantallas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.extraordinarios_jro.Dialogo
import com.example.extraordinarios_jro.MainActivity
import com.example.extraordinarios_jro.R
import com.example.extraordinarios_jro.RespuestaWebserver
import com.example.extraordinarios_jro.Routes
import com.example.extraordinarios_jro.utilidades.*
import kotlin.system.exitProcess

@Composable
fun PantallaPrincipal (
    navController: NavController,
    respuesta: RespuestaWebserver
) {
    Column (
        modifier = Modifier
            .padding(vertical = 100.dp, horizontal = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // Bloquear botón de registro en caso de error
        val permitirRegistro = !respuesta.hayError.value

        val dialogo = Dialogo(
            remember { mutableStateOf("Error") },
            remember { mutableStateOf("") },
            remember { mutableStateOf(false) },
            remember { mutableStateOf({}) }
        )

        // Mostrar mensaje de error, en caso de que lo haya
        if(respuesta.hayError.value) {
            dialogo.mensaje.value = respuesta.error.value
            dialogo.mostrar.value = true
        }
        Spacer (modifier = Modifier.height(50.dp))

        // Imagen alusiva
        Imagen (R.drawable.portada_img)

        // Cuadro de dialogo (mensajes de error)
        MensajeSimple (dialogo)

        // Bienvenida
        Spacer (modifier = Modifier.height(50.dp))
        Subtitulo ("Bienvenido al registro para examenes extraordinarios")
        Spacer (modifier = Modifier.height(20.dp))
        Texto (
            "Para agregar un nuevo registro de estudiante, selecciona la opción de 'Registro'.",
            1f
        )
        Spacer (modifier = Modifier.height(30.dp))

        // Nuevo registro
        BotonPrimario ("Registro", true, permitirRegistro) {
            navController.navigate (Routes.PantallaRegistro.route)
        }

        // Salir de la aplicación
        BotonSecundario ("Salir", true, true) {
            MainActivity().finish()
            exitProcess(0)
        }
        Spacer (modifier = Modifier.height(30.dp))

        // Mostrar datos personales
        Informacion ("Datos personales")
        {
            FilaDatos (etiqueta = "Nombre:", dato = "Jonathan Ruiz")
            FilaDatos (etiqueta = "Matrícula:", dato = "ES202100495")
            FilaDatos (etiqueta = "Grupo:", dato = "DS-DPMO-2401-B2-002")
            FilaDatos (etiqueta = "Fecha:", dato = "05/06/24")
            FilaDatos (etiqueta = "Figura académica:", dato = "Verónica Espinosa Romo")
        }
    }
}