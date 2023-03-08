package kfunkies.errors

enum class UserError(
    private val code: Int,
    private val message: String
) : SystemErrorCode {
    ACCESS_DENIED(code = 100, message = "Access is denied for this user");

    override fun getCode() = code
    override fun id() = name
    override fun getMessage() = message
}