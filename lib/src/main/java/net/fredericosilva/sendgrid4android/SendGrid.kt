package net.fredericosilva.sendgrid4android

import net.fredericosilva.sendgrid4android.api.SendGridApiService
import net.fredericosilva.sendgrid4android.models.Mail

class SendGrid(val callback: Callback? = null) {

    companion object {
        fun init(apiKey: String, debug: Boolean = false) {
            SendGridApiService.init(apiKey, debug)
        }
    }

    fun send(mail: Mail) {
        SendGridApiService.sendEmail(mail, callback)
    }

    fun send(mail: Mail.SimpleMail) {
        send(mail.getMail())
    }

    interface Callback {
        fun onFailure()
        fun onSuccess()
    }
}