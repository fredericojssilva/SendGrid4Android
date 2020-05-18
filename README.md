# SendGrid4Android
SendGrid library for Android

[![](https://jitpack.io/v/fredericojssilva/SendGrid4Android.svg)](https://jitpack.io/#fredericojssilva/SendGrid4Android)


## Download
Grab it via Gradle:

```implementation 'com.github.fredericojssilva:SendGrid4Android:0.1'```

## Usage

Extended:
```
//init lib
SendGrid.init(API_KEY)

val to = listOf(Personalizations(listOf(Email(toEmail))))
val mail = Mail(
            personalizations = listOf(Personalizations(listOf(Email(toEmail)))),
            from = Email(fromEmail),
            subject = subject,
            content = listOf(Content("text/plain", content))
        )

/**
 * Attachments
 */

// option 1: pass base64 file
 mail.attachments = listOf(Attachment(base64 = base64, filename = "file.extension"))

// option 2: pass the file
 mail.attachments = listOf(Attachment(file= file, filename = "file.extension"))

SendGrid().send(mail)

```

Simple:
```
//init lib
SendGrid.init(API_KEY)
val mail = Mail.SimpleMail(toEmail, listOf(fromEmail), subject, content)
SendGrid().send(mail)

```
