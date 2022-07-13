package com.example.chatapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class SignUp : AppCompatActivity() {

    private lateinit var edtname:EditText
    private lateinit var edtemail: EditText
    private lateinit var edtpassword: EditText
    private lateinit var btnsignup: Button
    private lateinit var mAuth : FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        mAuth= FirebaseAuth.getInstance()

        edtname = findViewById(R.id.edt_name)
        edtpassword = findViewById(R.id.edt_password)
        edtemail = findViewById(R.id.edt_email)
        btnsignup = findViewById(R.id.btn_signup)

        btnsignup.setOnClickListener{
            val email = edtemail.text.toString()
            val password = edtpassword.text.toString()

            signup(email,password)
        }
    }

    private fun signup(email:String, password:String){
        //logic for creating user

        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    //code for jumping to home
                    val intent= Intent(this@SignUp,MainActivity::class.java)
                    startActivity(intent)

                } else {
                    Toast.makeText(this@SignUp, "some error occurred", Toast.LENGTH_SHORT).show()

                }
            }
    }

}