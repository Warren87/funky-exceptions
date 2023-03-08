package kfunkies.errors

enum class DataError(
    private val code: Int,
    private val message: String
) : SystemErrorCode {
    NOT_EXISTS(code = 200, message = "Data with id {} doesn't exists"),
    NO_ACCESS(code = 201, message = "Access to data is forbidden");

    override fun getCode() = code
    override fun id() = name
    override fun getMessage() = message
}