package net.fredericosilva.sendgrid4android.api

import net.fredericosilva.sendgrid4android.models.Mail
import net.fredericosilva.sendgrid4android.models.SendGridBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal object SendGridApiService {
    const val BASEURL = "https://api.sendgrid.com/v3/"
    private lateinit var service: SendGridApiInterface

    fun init(token: String, debug : Boolean) {
        val interceptor = HttpLoggingInterceptor()

        if (debug) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS)
        }

        val client = OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor {
            val request: Request =
                it.request().newBuilder().addHeader("Authorization", "Bearer $token").build()
            it.proceed(request)
        }.build()
        Retrofit.Builder()
            .client(client)
            .baseUrl(BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().also {
                service = it.create(SendGridApiInterface::class.java)
            }
    }

    fun sendEmail(body: Mail) {
        service.sendEmail(body).execute()

    }


}