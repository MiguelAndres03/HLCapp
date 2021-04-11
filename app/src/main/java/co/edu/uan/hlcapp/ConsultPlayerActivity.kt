package co.edu.uan.hlcapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_auth.*
import kotlinx.android.synthetic.main.activity_consult_player.*
import java.lang.StringBuilder

class ConsultPlayerActivity : AppCompatActivity() {
    var db = FirebaseDatabase.getInstance().getReference("jugadores")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consult_player)

        //Obtener Nombres
        var getdata = object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var sb = StringBuilder()
                for (i in snapshot.children){
                    var nombre = i.child("nombre").getValue()
                    var apellido = i.child("apellido").getValue()
                    sb.append("${i.key} ${nombre} ${apellido}\n")
                }
                verDatos.setText(sb)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }
        db.addValueEventListener(getdata)
        db.addListenerForSingleValueEvent(getdata)
    }


}