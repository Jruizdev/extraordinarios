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
import com.example.extraordinarios_jro.utilidades.*
import org.json.JSONObject

@Composable
fun PantallaRegistro (navController: NavController, carreras: Array<String>?) {

    val placeholder_carrera = "Seleccionar carrera"
    val dialogo = Dialogo (
        remember { mutableStateOf("Error") },
        remember { mutableStateOf("") },
        remember { mutableStateOf(false) },
        remember { mutableStateOf({}) }
    )
    // Datos
    val matricula =  remember { mutableStateOf("") }
    val nombre =     remember { mutableStateOf("") }
    val apellido_p = remember { mutableStateOf("") }
    val apellido_m = remember { mutableStateOf("") }
    val sexo =       remember { mutableStateOf("") }
    val edad =       remember { mutableStateOf("") }
    val grupo =      remember { mutableStateOf("") }
    val pass =       remember { mutableStateOf("") }
    val pass_check = remember { mutableStateOf("") }
    val id_carrera =    remember { mutableStateOf(placeholder_carrera) }

    val registrar = remember { mutableStateOf(false) }
    val datos = remember { mutableStateOf(JSONObject()) }

    Column (
        modifier = Modifier
            .padding(vertical = 100.dp, horizontal = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // Solicitar datos de estudiante
        Spacer (modifier = Modifier.height(30.dp))
        EntradaTexto("Matrícula", matricula)
        EntradaTexto("Nombre", nombre)
        EntradaTexto("Apellido paterno", apellido_p)
        EntradaTexto("Apellido materno", apellido_m)

        // Selección de sexo
        Spacer(modifier = Modifier.height(10.dp))
        SeleccionRadio(
            "Sexo:",
            arrayOf("Masculino", "Femenino"),
            sexo
        )
        Spacer(modifier = Modifier.height(10.dp))
        EntradaNumerica("Edad", edad)
        EntradaTexto("Grupo", grupo)

        // Spinner para la selección de carrera
        Desplegable(carreras ?: arrayOf(), id_carrera)
        EntradaPass("Contraseña", pass)
        EntradaPass("Confirmar contraseña", pass_check)
        Spacer(modifier = Modifier.height(20.dp))

        // Cuadro de diálogo
        MensajeSimple (dialogo)

        // Agregar registro, una vez que haya sido validado
        AgregarRegistro (navController, datos.value, dialogo, registrar)

        BotonPrimario("Guardar", true, true) {

            // Crear JSON con los datos ingresados
            datos.value.put("matricula", matricula.value)
            datos.value.put("nombre", nombre.value)
            datos.value.put("apellido_p", apellido_p.value)
            datos.value.put("apellido_m", apellido_m.value)
            datos.value.put("sexo", sexo.value)
            datos.value.put("edad", edad.value)
            datos.value.put("grupo", grupo.value)
            datos.value.put("id_carrera", id_carrera.value)
            datos.value.put("pass", pass.value)
            datos.value.put("pass_check", pass_check.value)
            datos.value.put("placeholder_carrera", placeholder_carrera)

            // Validar datos del registro
            registrar.value = datosValidos (datos.value, dialogo)
        }
    }
}