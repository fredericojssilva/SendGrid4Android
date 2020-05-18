package net.fredericosilva.sendgrid4android.models

data class Mail(
    var personalizations: List<Personalizations>,
    var from: Email,
    var subject: String? = null,
    var content: List<Content> = ArrayList(),
    var attachments: List<Attachment>? = null
) {
    data class SimpleMail(
        val from: String,
        val to: List<String>,
        val subject: String?,
        val content: String,
        val attachments: List<Attachment>?
    ) {
        fun getMail(): Mail {
            val toEmailList = ArrayList<Email>()
            for (email in to) {
                toEmailList.add(Email(email))
            }

            return Mail(
                personalizations = listOf(
                    Personalizations(to = toEmailList)
                ),
                from = Email(from),
                subject = subject,
                content = listOf(Content(type = "text/plain", value = content)),
                attachments = attachments
            )
        }
    }
}