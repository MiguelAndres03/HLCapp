package co.edu.uan.hlcapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    var db = FirebaseDatabase.getInstance().getReference("jugadores")
    private val File = 1


        override fun onCreate(savedInstanceState: Bundle?) {

            title="Registro de Usuario"

            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_register)

            val listadep = findViewById<Spinner>(R.id.spDepartament)
            listadep.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    municipiosSeleccionados (view as TextView)
                }
                override fun onNothingSelected(parent: AdapterView<*>?) {
                   //
                }
            }

            //spiner tipo id

            val spTypeId = findViewById<Spinner>(R.id.spTypeId)
            val listTipoId = resources.getStringArray(R.array.TipoIdentificacion)
            if (spTypeId != null){
                val adaptadorIden = ArrayAdapter(this, R.layout.spinner_item_tipoidentificacion, listTipoId)
                spTypeId.adapter = adaptadorIden
                spTypeId.onItemSelectedListener = object :
                    AdapterView.OnItemSelectedListener{
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                    }
                    override fun onNothingSelected(parent: AdapterView<*>?) {
                    }
                }

            }

            //spiner tipo posicion
            val spPosicion = findViewById<Spinner>(R.id.spPosicion)
            val listPosicion = resources.getStringArray(R.array.PosiciónCampo)
            if (spPosicion != null){
                val adaptadorPosicion = ArrayAdapter(applicationContext, R.layout.spinner_item_tipoidentificacion, listPosicion)
                spPosicion.adapter = adaptadorPosicion

                spPosicion.onItemSelectedListener = object :
                    AdapterView.OnItemSelectedListener{
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                    }
                    override fun onNothingSelected(parent: AdapterView<*>?) {
                    }
                }

            }

            //spiner Pie Habil
            val spPieHabil = findViewById<Spinner>(R.id.spPieHabil)
            val listPieHabil= resources.getStringArray(R.array.PieHabil)
            if (spPosicion != null){
                val adaptadorPieHabil = ArrayAdapter(this, R.layout.spinner_item_tipoidentificacion, listPieHabil)
                spPieHabil.adapter = adaptadorPieHabil

                spPieHabil.onItemSelectedListener = object :
                    AdapterView.OnItemSelectedListener{
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                    }
                    override fun onNothingSelected(parent: AdapterView<*>?) {

                    }
                }

            }

            etDate.setOnClickListener {
                showDatePickerDialog()
            }

            saveregisterButton.setOnClickListener{
                    var tipoid = spTypeId.toString()
                    var id = editId.text.toString()
                    var nombre = nameEditText.text.toString()
                    var apellido = surnameEditText.text.toString()
                    var fechanacimiento = etDate
                    var edad = editEdad.text.toString()
                    var departamento = spDepartament
                    var municipio = spMunicipio
                    var telefono = editPhone.text.toString()
                    var email = editEmail.text.toString()
                    var peso = editWeight.text.toString()
                    var posicion = spPosicion
                    var piehabil = spPieHabil
                    var club = editClub.text.toString()
                    var patologia = editPatologia.text.toString()

                db.child(id).setValue(Players(tipoid,
                    nombre,
                    apellido,
                    fechanacimiento.text.toString(),
                    edad,
                    departamento,
                    municipio,
                    telefono,
                    email,
                    peso,
                    posicion.toString(),
                    piehabil.toString(),
                    club, patologia))

                if (it.isEnabled){
                    volverHome()
                    Toast.makeText(this, "Deportista registrado", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, "Algo salio mal, ", Toast.LENGTH_SHORT).show()
                }

            }
           /*fotoButton.setOnClickListener{
                fileUpload()
            }*/

        }

    private fun volverHome(){
        val volver = Intent(this, HomeActivity::class.java)
        startActivity(volver)
    }

    fun municipiosSeleccionados (text: TextView){
        Log.v("MYDEBUG", "Item seleccionado: ${text.text}")
        val municipiosArray = resources.getIdentifier(text.text.toString().toLowerCase()+"_municipios","array",packageName)
        ArrayAdapter.createFromResource(
                this,
                municipiosArray,
               R.layout.spinner_item_tipoidentificacion
        ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.spinner_item_tipoidentificacion)
            val listmuni = findViewById<Spinner>(R.id.spMunicipio)
            listmuni.adapter = adapter
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == File){
            if (resultCode == RESULT_OK){
                val FileUri = data!!.data
                val Folder: StorageReference =
                    FirebaseStorage.getInstance().getReference().child("jugadores")
                val file_name: StorageReference = Folder.child("imagen_jugador"+FileUri!!.lastPathSegment)
                file_name.putFile(FileUri).addOnSuccessListener { taskSnapshot ->
                    file_name.downloadUrl.addOnSuccessListener { uri ->
                        val hashMap =
                            HashMap<String, String>()
                        hashMap["url"] = java.lang.String.valueOf(uri)
                        db.setValue(hashMap)
                        /* db.collection("jugadores").document(editId.toString()).set(
                             hashMapOf("url" to uri)
                         )*/
                        Toast.makeText(this, "La foto se subio correctamente", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    fun fileUpload(){
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "*/*"
        startActivityForResult(intent,File)
    }

    private fun onDateSelected(day: Int, month: Int, year: Int) {
        etDate.setText("$day/$month/$year")
    }

    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
        datePicker.show(supportFragmentManager, "datePicker")
    }

}