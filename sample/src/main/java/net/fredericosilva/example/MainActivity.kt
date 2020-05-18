package net.fredericosilva.example

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import kotlinx.android.synthetic.main.activity_main.*
import net.fredericosilva.sendgrid4android.SendGrid
import net.fredericosilva.sendgrid4android.models.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        send.setOnClickListener {
            sendEmail(toEmailEditText.text.toString(), contentEmailEditText.text.toString())
        }
    }

    private fun sendEmail(toEmail: String, content: String) {
        val fromEmail = "example@email.com"
        val mail = Mail(
            personalizations = listOf(Personalizations(listOf(Email(toEmail)))),
            from = Email(fromEmail),
            subject = "Subject",
            content = listOf(Content("text/plain", content))
        )

        /**
         * Attachments
         */

        // option 1: pass base64 file
        // mail.attachments = listOf(Attachment(base64 = base64, filename = "file.extension"))

        // option 2: pass the file
        // mail.attachments = listOf(Attachment(file= file, filename = "file.extension"))

        SendGrid().send(mail)
    }
}
