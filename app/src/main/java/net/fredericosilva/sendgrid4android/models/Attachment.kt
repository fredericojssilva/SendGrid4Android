package net.fredericosilva.sendgrid4android.models

import android.util.Base64
import java.io.File

class Attachment() {
    lateinit var filename: String
    lateinit var content: String

    constructor(filename: String, file: File) : this() {
        this.filename = filename
        this.content = Base64.encodeToString(file.readBytes(), Base64.DEFAULT)

    }
    constructor(filename: String, base64: String) : this() {
        this.filename = filename
        this.content = base64

    }
}