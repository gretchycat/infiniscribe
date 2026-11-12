package com.infinifolder.viewer

import io.github.gretchycat.infiniscribe.DosTextEncoding
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class AnsiSourceTextTest {
    @Test fun escapeIsVisibleAsLeftArrowAndOtherBytesUseTheChosenCodePage() {
        val raw = byteArrayOf(0x1b, '['.code.toByte(), '3'.code.toByte(), '1'.code.toByte(), 'm'.code.toByte(), 0x82.toByte())
        val decoded = String(raw, DosTextEncoding.cp437)

        assertEquals("←[31mé", AnsiSourceText.display(decoded))
    }

    @Test fun savingTheVisibleEscapeRestoresTheEscapeByte() {
        val bytes = AnsiSourceText.encode("←[31mé", DosTextEncoding.cp437)

        assertEquals(0x1b, bytes[0].toInt() and 0xff)
        assertTrue(String(bytes, DosTextEncoding.cp437).startsWith("\u001b[31m"))
        assertEquals("←[31mé", AnsiSourceText.display(String(bytes, DosTextEncoding.cp437)))
    }
}
