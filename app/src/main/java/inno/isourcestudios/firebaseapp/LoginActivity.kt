package inno.isourcestudios.firebaseapp

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private val mAuth: FirebaseAuth? = null
    private lateinit var auth: FirebaseAuth
    private lateinit var emailEt:EditText
    private lateinit var passwordEt:EditText
    private lateinit var loginBtn:Button
    private lateinit var createBtn:Button
    private lateinit var resetPassTx:TextView

    private var mFirebaseAnalytics: FirebaseAnalytics? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        emailEt = findViewById(R.id.emaileditlog)
        passwordEt = findViewById(R.id.passwordeditlog)
        loginBtn = findViewById(R.id.loginbtnlog)
        createBtn = findViewById(R.id.createbtnlog)
        resetPassTx = findViewById(R.id.resetpasslog)
        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance();
        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)

        loginBtn.setOnClickListener {
            var email: String = emailEt.text.toString()
            var password: String = passwordEt.text.toString()
            if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                //TextUtils IS A STRING SPLITTER
                Toast.makeText(this@LoginActivity, "Please fill all the fields", Toast.LENGTH_LONG).show()
            } else{
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, OnCompleteListener { task ->
                    if(task.isSuccessful) {
                        Toast.makeText(this, "Successfully Logged In", Toast.LENGTH_LONG).show()
                        val intent = Intent(this, HomeActivity::class.java)
                        startActivity(intent)
                        finish()
                    }else {
                        Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG).show()
                    }
                })
            }
        }

        createBtn.setOnClickListener{
            val intent = Intent(this, CreatAccountActivity::class.java)
            startActivity(intent)
            finish()
        }

        resetPassTx.setOnClickListener{
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }
    }
}