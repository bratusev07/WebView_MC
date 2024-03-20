package ru.bratusev.webview_mc

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<AppCompatButton>(R.id.login_button).setOnClickListener {
            startActivity(Intent(applicationContext, WebViewScreen::class.java).putExtra("ACCOUNT_ID", loginUser()))
        }
    }

    private fun loginUser() : Int{
        val mail = findViewById<EditText>(R.id.email_input).text.toString()
        val pass = findViewById<EditText>(R.id.pass_input).text.toString()

        return if(mail == "my@mail.ru" && pass == "12345") 1
        else 2
    }
}