package kfunkies

import kfunkies.errors.SystemErrorCode
import java.util.*

private const val DEFAULT_MESSAGE = "System Error"
object LocalizedMessages {

    private val ERROR_MESSAGES = collectLocalizedErrorMessages()
    private fun collectLocalizedErrorMessages() = Lang.values().associateWith { lang -> loadResourceFor(lang.code()) }

    private fun loadResourceFor(langCode: String) = Properties().also {
        it.load(ClassLoader.getSystemResourceAsStream("error_$langCode.properties"))
    }

    fun message(lang: Lang, errorCode: SystemErrorCode) =
        ERROR_MESSAGES[lang]?.getProperty(errorCode.id(), DEFAULT_MESSAGE) ?: DEFAULT_MESSAGE

}

enum class Lang {
    EN,
    PL;

    fun code() = name.lowercase()
}

