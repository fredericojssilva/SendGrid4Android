package net.fredericosilva.example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import net.fredericosilva.sendgrid4android.SendGrid
import net.fredericosilva.sendgrid4android.models.*

class MainActivity : AppCompatActivity() {

    val API_KEY = "#APIKEY"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        SendGrid.init(API_KEY, debug = true)

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
