package com.example.extraordinarios_jro

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.android.volley.toolbox.Volley
import com.example.extraordinarios_jro.ui.theme.Extraordinarios_JROTheme
import com.example.extraordinarios_jro.pantallas.*
import com.example.extraordinarios_jro.utilidades.BarraSuperior
import com.example.extraordinarios_jro.utilidades.carrerasDisponibles

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Extraordinarios_JROTheme {
                Inicio ()
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun Inicio () {

    val navController = rememberNavController()
    var tituloApp by remember { mutableStateOf("Titulo") }

    // Respuesta del webserver
    val respuesta = RespuestaWebserver (
        remember { mutableStateOf(arrayOf()) },
        remember { mutableStateOf("") },
        remember { mutableStateOf(false) },
        remember { mutableStateOf(false) }
    )
    // Crear petición para obtener carreras disponibles
    val peticion = carrerasDisponibles (respuesta)

    // Realizar petición con Volley
    val volleyRequest = Volley.newRequestQueue (LocalContext.current)
    volleyRequest.add (peticion)

    Scaffold (
        topBar = {
            BarraSuperior (titulo = tituloApp)
        }
    ){
        NavHost(
            modifier = Modifier.verticalScroll(rememberScrollState()),
            navController = navController,
            startDestination = Routes.PantallaPrincipal.route
        ){

            // Definir rutas de la navegación
            composable (Routes.PantallaPrincipal.route) {
                tituloApp = "Exámenes extraordinarios"
                PantallaPrincipal (navController, respuesta)
            }
            composable (Routes.PantallaRegistro.route) {
                tituloApp = "Nuevo registro"
                PantallaRegistro (navController, respuesta.resultado.value)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Previsualizacion() {
    Extraordinarios_JROTheme {
        Inicio ()
    }
}