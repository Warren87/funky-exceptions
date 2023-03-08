package kfunkies

import kfunkies.errors.SystemErrorCode
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}
data class SystemException constructor(
    val errorCode: SystemErrorCode,
    override val message: String = errorCode.getMessage()
) : Exception(message) {
    
    companion object {
        fun andLog(errorCode: SystemErrorCode) = SystemException(errorCode).also { logger.error { it.message }}
        fun andLog(errorCode: SystemErrorCode, vararg arguments : Any) = SystemException(errorCode)
            .also { logger.error(errorCode.getMessage(), arguments)}
        
        
    }
}
