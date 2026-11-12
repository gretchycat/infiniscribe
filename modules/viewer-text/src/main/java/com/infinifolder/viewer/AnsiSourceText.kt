package com.infinifolder.viewer

import java.nio.charset.Charset

/** Display raw ANSI source safely while retaining an editable ESC representation. */
internal object AnsiSourceText {
    fun display(text: String): String = text.replace('\u001b', '←')

    fun encode(text: String, charset: Charset): ByteArray =
        text.replace('←', '\u001b').toByteArray(charset)
}
