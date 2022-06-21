package com.anastasiyayuragina.mealplanner.data

import androidx.annotation.StringRes
import com.anastasiyayuragina.mealplanner.R
import java.net.UnknownHostException

sealed class AppException(message: String?) : Throwable(message) {
    class Api(val code: Int, message: String?) : AppException(message)
    class Id(
        @StringRes val descriptionId: Int,
        @StringRes val titleId: Int = R.string.error
    ) : AppException(null)

    class NoConnection : AppException(null)
    class Common(override val message: String) : AppException(message)
}

fun Throwable.toAppException(): AppException =
    when (this) {
//        is ServerException -> AppException.Api(this.error.code, this.error.message)
        is UnknownHostException -> AppException.NoConnection()
        else -> AppException.Common(this.message.orEmpty())
    }