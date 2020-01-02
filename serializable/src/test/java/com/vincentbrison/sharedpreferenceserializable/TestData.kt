package com.vincentbrison.sharedpreferenceserializable

import java.io.Serializable

data class DummySerializableData(val value: String, val otherValue: Int) : Serializable

data class OtherDummySerializableData(val otherValue: String) : Serializable
