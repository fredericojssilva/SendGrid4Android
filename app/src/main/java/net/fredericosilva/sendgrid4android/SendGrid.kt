package net.fredericosilva.sendgrid4android

import net.fredericosilva.sendgrid4android.api.SendGridApiService
import net.fredericosilva.sendgrid4android.models.Mail
import net.fredericosilva.sendgrid4android.models.SendGridBody


class SendGrid() {

    companion object{
        fun init(apiKey: String, debug: Boolean)
        {
            SendGridApiService.init(apiKey, debug)
        }
    }

    fun send(mail: Mail) {

        SendGridApiService.sendEmail(mail)
    }

}