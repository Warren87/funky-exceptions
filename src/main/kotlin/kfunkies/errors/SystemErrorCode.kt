package kfunkies.errors

sealed interface SystemErrorCode {
    
    fun getCode() : Int
    
    fun getMessage() = "System Error"
    fun id() = getCode().toString()
}