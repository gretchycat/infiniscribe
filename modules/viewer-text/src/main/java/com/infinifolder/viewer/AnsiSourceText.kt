package com.infinifolder.viewer

import java.nio.charset.Charset

/** Display raw ANSI source safely while retaining an editable ESC representation. */
internal object AnsiSourceText {
    private const val ESCAPE_DISPLAY = "←\uFE0E"

    fun display(text: String): String = text.replace("\u001b", ESCAPE_DISPLAY)

    fun source(text: String): String = text.replace(ESCAPE_DISPLAY, "\u001b")

    fun encode(text: String, charset: Charset): ByteArray =
        source(text).toByteArray(charset)
}
