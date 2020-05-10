package net.fredericosilva.sendgrid4android.models

data class Mail(
    var personalizations: List<Personalizations>,
    var from: Email,
    var subject: String? = null,
    var content: List<Content> = ArrayList(),
    var attachments: List<Attachment>? = null
)