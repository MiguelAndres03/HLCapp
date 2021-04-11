package co.edu.uan.hlcapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val bundle= intent.extras
        val email = bundle?.getString("email")

        setup(email?: "")

        val registerbtn = findViewById<Button>(R.id.registerButton)
        registerbtn.setOnClickListener{openRegisterActivity()}

        val consultplayerbtn = findViewById<Button>(R.id.consultButton)
        consultplayerbtn.setOnClickListener{openConsultActivity()}
       }

    private fun openRegisterActivity(){
        val nextIntent1 = Intent(this, RegisterActivity::class.java)
        startActivity(nextIntent1)
    }

    private fun openConsultActivity(){
        val nextIntent2 = Intent(this, ConsultPlayerActivity::class.java)
        startActivity(nextIntent2)
    }

    private fun setup(email: String){

        title="Inicio"
        emailTextView.text = "Hola Entrenador"

        logoutButton.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            onBackPressed()
            Toast.makeText(this, "Salio Correctamente", Toast.LENGTH_SHORT).show()
        }
    }



}