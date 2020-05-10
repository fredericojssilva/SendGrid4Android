package net.fredericosilva.sendgrid4android.api

import net.fredericosilva.sendgrid4android.models.Mail
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

internal interface SendGridApiInterface {
    @POST("mail/send")
    fun sendEmail(@Body body: Mail) : Call<Void>

}