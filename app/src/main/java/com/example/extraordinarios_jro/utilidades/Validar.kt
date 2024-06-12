package com.example.extraordinarios_jro.utilidades

import com.example.extraordinarios_jro.Dialogo
import org.json.JSONObject

fun datosValidos (datos: JSONObject, dialogo: Dialogo) : Boolean {

    // Comprobar que todos los campos hayan sido llenados
    if (datos.get("matricula")  == "" ||
        datos.get("nombre")     == "" ||
        datos.get("apellido_p") == "" ||
        datos.get("apellido_m") == "" ||
        datos.get("sexo")       == "" ||
        datos.get("edad")       == "" ||
        datos.get("grupo")      == "" ||
        datos.get("pass")       == "" ||
        datos.get("pass_check") == "" ||
        datos.get("id_carrera")    == datos.get("placeholder_carrera"))
    {
        dialogo.mensaje.value = "Es necesario llenar todos los campos para poder continuar."
        dialogo.mostrar.value = true
        return false
    }
    // Validar contraseñas
    if(datos.get("pass") != datos.get("pass_check"))
    {
        dialogo.mensaje.value = "Las contraseñas ingresadas no coinciden."
        dialogo.mostrar.value = true
        return false
    }

    // Los datos son válidos
    return true
}