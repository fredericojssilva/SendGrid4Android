package net.fredericosilva.sendgrid4android.api

import android.util.Log
import net.fredericosilva.sendgrid4android.SendGrid
import net.fredericosilva.sendgrid4android.models.Mail
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal object SendGridApiService {
    const val TAG = "SendGridApiService"

    const val BASEURL = "https://api.sendgrid.com/v3/"
    private lateinit var service: SendGridApiInterface

    fun init(token: String, debug: Boolean) {
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

    fun sendEmail(body: Mail, callback: SendGrid.Callback?) {
        service.sendEmail(body).enqueue(object : Callback<Void> {
            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.e(TAG, "send email failed", t)
                callback?.onFailure()
            }

            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    callback?.onSuccess()
                } else {
                    Log.e(TAG, "send email failed: ${response.code()}")
                    callback?.onFailure()
                }
            }

        })

    }

}