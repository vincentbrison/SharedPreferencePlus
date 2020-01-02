package com.vincentbrison.sharedpreferenceobject

import android.content.SharedPreferences

fun <T> SharedPreferences.Editor.putObject(
    key: String,
    value: T,
    objectEncoder: ObjectEncoder<T>
): SharedPreferences.Editor {
    return putString(key, objectEncoder.encode(value))
}

inline fun <reified T> SharedPreferences.getObject(
    key: String,
    objectEncoder: ObjectEncoder<T>
): T? {
    val string = getString(key, null) ?: return null
    return objectEncoder.decode(string)
}

interface ObjectEncoder<T> {
    fun encode(value: T): String
    fun decode(encodedString: String): T
}
