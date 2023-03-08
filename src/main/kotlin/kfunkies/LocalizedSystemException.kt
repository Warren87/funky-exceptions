package kfunkies

import kfunkies.errors.SystemErrorCode

class LocalizedSystemException private constructor(
    val errorCode: SystemErrorCode,
    override val message: String
) : Exception(message) {
    
    companion object {
        
        fun localized(lang: Lang, errorCode: SystemErrorCode) = LocalizedSystemException(
            errorCode, 
            message = LocalizedMessages.message(lang, errorCode)
        )
        
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as LocalizedSystemException

        if (errorCode != other.errorCode) return false

        return true
    }

    override fun hashCode(): Int {
        return errorCode.hashCode()
    }
    
}