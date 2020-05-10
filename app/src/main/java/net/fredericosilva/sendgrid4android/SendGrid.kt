package net.fredericosilva.sendgrid4android

import net.fredericosilva.sendgrid4android.api.SendGridApiService
import net.fredericosilva.sendgrid4android.models.Mail
import net.fredericosilva.sendgrid4android.models.SendGridBody


class SendGrid(apiKey: String) {

    fun send(mail: Mail) {

        SendGridApiService.sendEmail(mail)
    }

}