package co.edu.uan.hlcapp

import android.widget.Spinner

class Players {
    var tipoid = ""
    var nombre = ""
    var apellido = ""
    var fechanacimiento = ""
    var edad = ""
    var departamento = ""
    var municipio = ""
    var telefono = ""
    var email = ""
    var peso = ""
    var posicion = ""
    var piehabil = ""
    var club = ""
    var patologia = ""
    constructor(tipoid:String,
                nombre:String,
                apellido:String,
                fechanacimiento: String,
                edad:String,
                departamento: Spinner,
                municipio: Spinner,
                telefono:String,
                email:String,
                peso:String,
                posicion: String,
                piehabil: String,
                club:String,
                patologia:String){

                    this.tipoid = tipoid
                    this.nombre = nombre
                    this.apellido = apellido
                    this.fechanacimiento = fechanacimiento
                    this.edad = edad
                    this.departamento = departamento.toString()
                    this.municipio = municipio.toString()
                    this.telefono = telefono
                    this.email = email
                    this.peso = peso
                    this.posicion = posicion
                    this.piehabil = piehabil
                    this.club = club
                    this.patologia = patologia
    }
}