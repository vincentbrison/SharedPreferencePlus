package com.vincentbrison.sharepreferenceobject
import android.content.Context
import androidx.core.content.edit
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.vincentbrison.sharedpreferenceobject.ObjectEncoder
import com.vincentbrison.sharedpreferenceobject.putObject
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SharedPreferenceObjectTests {

    private val appContext = InstrumentationRegistry.getInstrumentation().targetContext
    private val sharedPreferences = appContext.getSharedPreferences("test", Context.MODE_PRIVATE)
    private val encoder = TestObjectEncoder()

    @Test
    fun `test encoder is correct`() {
        val value = TestObject(1)
        assertEquals(encoder.decode(encoder.encode(value)), value)
    }

    @Test
    fun `test put actually put object in shared preferences`() {
        assertFalse(sharedPreferences.contains("test"))
        sharedPreferences.edit(commit = true) {
            putObject("test", TestObject(1), encoder)
        }
        assertTrue(sharedPreferences.contains("test"))
    }
}

class TestObjectEncoder : ObjectEncoder<TestObject> {
    override fun encode(value: TestObject): String = value.value.toString()
    override fun decode(encodedString: String) = TestObject(encodedString.toInt())
}

data class TestObject(val value: Int)