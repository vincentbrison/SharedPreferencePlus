package com.vincentbrison.sharedpreferenceserializable

import android.content.SharedPreferences
import com.vincentbrison.sharedpreferenceobject.ObjectEncoder
import com.vincentbrison.sharedpreferenceobject.getObject
import com.vincentbrison.sharedpreferenceobject.putObject
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.io.Serializable

object Serializer : ObjectEncoder<Serializable> {
    private val charset = Charsets.ISO_8859_1

    override fun encode(value: Serializable): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        val outputStream = ObjectOutputStream(byteArrayOutputStream)
        outputStream.writeObject(value)
        outputStream.close()
        byteArrayOutputStream.close()
        return String(byteArrayOutputStream.toByteArray(), charset)
    }

    override fun decode(encodedString: String): Serializable {
        val byteArrayInputStream = ByteArrayInputStream(
            encodedString.toByteArray(charset = charset)
        )
        val objectInputStream = ObjectInputStream(byteArrayInputStream)
        return try {
            objectInputStream.readObject() as Serializable
        } finally {
            byteArrayInputStream.close()
            objectInputStream.close()
        }
    }
}

fun <T : Serializable> SharedPreferences.Editor.putSerializable(
    key: String,
    value: T
): SharedPreferences.Editor {
    return putObject(key, value, Serializer)
}

inline fun <reified T : Serializable> SharedPreferences.getSerializable(
    key: String
): T? {
    return getObject(key, Serializer) as T?
}
