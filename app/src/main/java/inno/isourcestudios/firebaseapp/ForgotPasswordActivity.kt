package inno.isourcestudios.firebaseapp

import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordActivity : AppCompatActivity() {
    private val mAuth: FirebaseAuth? = null
    private lateinit var auth: FirebaseAuth
    private lateinit var emailEt: EditText
    private lateinit var resetPasswordBtn: Button
    private lateinit var back: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        auth = FirebaseAuth.getInstance()
        emailEt = findViewById(R.id.emaileditforgot)
        resetPasswordBtn = findViewById(R.id.resetpassbtn)
        back = findViewById(R.id.backtbtnpass)
        back.setOnClickListener {
            finish()
        }
        resetPasswordBtn.setOnClickListener {
            var email: String = emailEt.text.toString()
            if (TextUtils.isEmpty(email)) {
                Toast.makeText(this, "Please enter email id", Toast.LENGTH_LONG).show()
            } else {
                auth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(this, OnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Reset link sent to your email", Toast.LENGTH_LONG)
                                .show()
                        } else {
                            Toast.makeText(this, "Unable to send reset mail", Toast.LENGTH_LONG)
                                .show()
                        }
                    })
            }
        }
    }
}