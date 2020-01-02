package com.vincentbrison.sharedpreferenceserializable

import android.content.Context.MODE_PRIVATE
import androidx.core.content.edit
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SharedPreferenceSerializableTests {

    private val appContext = InstrumentationRegistry.getInstrumentation().targetContext
    private val sharedPreferences = appContext.getSharedPreferences("test", MODE_PRIVATE)

    @After
    fun clear() {
        sharedPreferences.edit(commit = true) { clear() }
    }

    @Test
    fun `test serializable write and read with same object`() {
        val dummyValue = DummySerializableData("a", 1)
        sharedPreferences.edit(commit = true) {
            putSerializable("key", dummyValue).commit()
        }
        val storedValue = sharedPreferences.getSerializable<DummySerializableData>("key")
        assertEquals(dummyValue, storedValue)
    }

    @Test(expected = ClassCastException::class)
    fun `test serializable write and read with different objects throw`() {
        val dummyValue = DummySerializableData("a", 1)
        sharedPreferences.edit(commit = true) {
            putSerializable("key", dummyValue).commit()
        }
        sharedPreferences.getSerializable<OtherDummySerializableData>("key")
    }

    @Test
    fun `test read without write return null`() {
        val storedValue = sharedPreferences.getSerializable<DummySerializableData>("key")
        assertNull(storedValue)
    }
}
