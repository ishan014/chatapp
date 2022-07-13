package com.example.chatapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth


class Login : AppCompatActivity() {

    private lateinit var edtemail: EditText
    private lateinit var edtpassword: EditText
    private lateinit var btnlogin: Button
    private lateinit var btnsignup: Button
    private lateinit var mAuth :FirebaseAuth




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()


        edtemail = findViewById(R.id.edt_email)
        edtpassword = findViewById(R.id.edt_password)
        btnlogin = findViewById(R.id.btn_login)
        btnsignup = findViewById(R.id.btn_signup)

        btnsignup.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }


        btnlogin.setOnClickListener {
            val email = edtemail.text.toString()
            val password = edtpassword.text.toString()

            login(email,password)

        }
    }


    private fun login(email:String,  password:String ){
       // logic for logging user
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    //code for logging in user
                    val intent= Intent(this@Login,MainActivity::class.java)
                    startActivity(intent)

                } else {
                    Toast.makeText(this@Login , "User does not exist", Toast.LENGTH_SHORT).show()
                }
            }



    }
}