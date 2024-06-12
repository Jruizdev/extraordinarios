package com.example.extraordinarios_jro.utilidades

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.extraordinarios_jro.Dialogo

@Composable
fun Imagen (imagen: Int) {
    Image(
        painter = painterResource(id = imagen),
        contentDescription = ""
    )
}
@Composable
fun MensajeSimple (
    dialogo: Dialogo
) {
    if(dialogo.mostrar.value) {
        AlertDialog(

            title = { Text (text = dialogo.titulo.value) },
            text = { Text (
                text = dialogo.mensaje.value,
                textAlign = TextAlign.Center,
                fontSize = 18.sp)
            },
            onDismissRequest = {
                dialogo.mostrar.value = false
           },
            confirmButton = {
                BotonPrimario(etiqueta = "Aceptar", fitWidth = true, activado = true) {
                    dialogo.mostrar.value = false
                    dialogo.onAccept.value ()
                }
            }
        )
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraSuperior (titulo: String) {
    TopAppBar (
        colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.inverseOnSurface
        ),
        title = {
            Text (
                modifier = Modifier
                    .padding(start = 30.dp),
                text = titulo,
                fontWeight = FontWeight.Bold
            )
        }
    )
}
@Composable
fun Subtitulo (texto: String) {
    Text (
        text = texto,
        fontSize = 25.sp,
        textAlign = TextAlign.Center,
        color = MaterialTheme.colorScheme.secondary
    )
}
@Composable
fun Texto (texto: String, escala: Float) {
    Text (
        modifier = Modifier
            .fillMaxWidth (escala),
        text = texto,
        fontSize = 18.sp,
        textAlign = TextAlign.Center
    )
}
@Composable
fun BotonPrimario (etiqueta: String, fitWidth: Boolean, activado: Boolean, onClick: () -> Unit) {
    Button (
        modifier = Modifier
            .fillMaxWidth(if (fitWidth) 1f else 0.5f)
            .padding(vertical = 10.dp),
        onClick = { onClick () },
        enabled = activado
    ) {
        Text (text = etiqueta)
    }
}
@Composable
fun BotonSecundario (etiqueta: String, fitWidth: Boolean, activado: Boolean, onClick: () -> Unit) {
    FilledTonalButton (
        modifier = Modifier
            .fillMaxWidth(if (fitWidth) 1f else 0.5f)
            .padding(vertical = 10.dp),
        onClick = { onClick () },
        enabled = activado
    ) {
        Text (text = etiqueta)
    }
}
@Composable
fun EntradaTexto (etiqueta: String, salida: MutableState<String>) {
    TextField (
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        value = salida.value,
        onValueChange = {salida.value = it},
        label = { Text(text = etiqueta) },
        singleLine = true
    )
}
@Composable
fun EntradaNumerica (etiqueta: String, salida: MutableState<String>) {
    val validacion = Regex("^([1-9]\\d*|)$")

    TextField (
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        value = salida.value,
        onValueChange = {
            if (it.matches(validacion)) salida.value = it
        },
        label = { Text (text = etiqueta) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true
    )
}
@Composable
fun EntradaPass (etiqueta: String, salida: MutableState<String>) {
    TextField (
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        value = salida.value,
        onValueChange = {salida.value = it},
        label = { Text(text = etiqueta) },
        visualTransformation = PasswordVisualTransformation (),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true
    )
}
@Composable
fun SeleccionRadio (
    etiqueta: String,
    opciones: Array<String>,
    seleccion: MutableState<String>
) {
    Column ( Modifier.fillMaxWidth() ) {
        Text (
            text = etiqueta,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Row (
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            opciones.forEach { opcion ->
                Row (
                    Modifier
                        .selectable(
                            selected = seleccion.value == opcion,
                            onClick = { seleccion.value = opcion }
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton (
                        selected = seleccion.value == opcion,
                        onClick = { seleccion.value = opcion }
                    )
                    Text (
                        text = opcion
                    )
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Desplegable (elementos: Array<String>, seleccionado: MutableState<String>) {

    var seleccion by remember { mutableStateOf (seleccionado.value) }
    var expandido by remember { mutableStateOf (false) }

    ExposedDropdownMenuBox (
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        expanded = expandido,
        onExpandedChange = { expandido = !expandido }
    ) {
        TextField (
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(),
            value = seleccion,
            onValueChange = {},
            readOnly = true,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon (expanded = expandido) }
        )

        ExposedDropdownMenu (
            modifier = Modifier.fillMaxWidth(),
            expanded = expandido,
            onDismissRequest = { expandido = false }
        ) {
            elementos.forEachIndexed { indice, texto ->
                DropdownMenuItem (
                    text = { Text (text = texto) },
                    onClick = {
                        seleccion = elementos [indice]
                        seleccionado.value = (indice + 1).toString()
                        expandido = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
}
@Composable
fun FilaDatos (etiqueta: String, dato: String) {
    Row {
        Text (
            modifier = Modifier
                .weight(0.5f),
            text = etiqueta,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text (
            modifier = Modifier
                .weight(1f),
            text = dato,
            fontSize = 18.sp,
        )
    }
}
@Composable
fun Informacion (titulo: String, cuerpo: @Composable () -> Unit) {
    Column (
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(vertical = 30.dp, horizontal = 20.dp)
    ) {
        Text (
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            text = titulo,
            fontSize = 22.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
        cuerpo ()
    }
}