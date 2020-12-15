package com.example.supermarketapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_auth.*

class AuthActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var signInOptions: GoogleSignInOptions
    private lateinit var signInClient: GoogleSignInClient
    private val googleSignIn: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        auth = FirebaseAuth.getInstance()
        initializeUI()
        setupLoginGoogle()
        setup()
    }

    override fun onStart() {
        super.onStart()
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            val showHome = Intent(this, HomeActivity::class.java)
            startActivity(showHome)
            finish()
        }
    }

    private fun setupLoginGoogle() {
        signInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
        signInClient = GoogleSignIn.getClient(this, signInOptions)
    }

    private fun initializeUI() {
        btnGoogle.setOnClickListener{
            login()
        }
    }

    private fun login() {
        val loginIntent: Intent = signInClient.signInIntent
        startActivityForResult(loginIntent, googleSignIn)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == googleSignIn){
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                if (account != null){
                    googleFirebaseAuth(account)
                }
            }catch (e: ApiException){
                Toast.makeText(this, "Error with the connection", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun googleFirebaseAuth(account: GoogleSignInAccount) {
        val credentials = GoogleAuthProvider.getCredential(account.idToken, null)
        auth.signInWithCredential(credentials).addOnCompleteListener{
            if (it.isSuccessful){
                val homeScreen = Intent(this, HomeActivity::class.java)
                startActivity(homeScreen)
            } else {
                Toast.makeText(this, "Error with the connection", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun setup() {
        btnRegister.setOnClickListener {
            if (txtEmail.text.isNotEmpty() && txtPassword.text.isNotEmpty()){
                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(txtEmail.text.toString(), txtPassword.text.toString()).addOnCompleteListener {
                        if (it.isSuccessful){
                            val home = Intent(this, HomeActivity::class.java)
                            startActivity(home)
                        } else {
                            showAlert()
                        }
                    }
            }
        }

        btnSignIn.setOnClickListener {
            if (txtEmail.text.isNotEmpty() && txtPassword.text.isNotEmpty()){
                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(txtEmail.text.toString(), txtPassword.text.toString()).addOnCompleteListener {
                        if (it.isSuccessful){
                            val home = Intent(this, HomeActivity::class.java)
                            startActivity(home)
                        } else {
                            showAlert()
                        }
                    }
            }
        }
    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("There was an error with your registration")
        builder.setPositiveButton("Continue", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}