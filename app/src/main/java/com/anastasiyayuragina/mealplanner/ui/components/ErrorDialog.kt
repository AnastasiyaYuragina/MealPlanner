package com.anastasiyayuragina.mealplanner.ui.components

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.anastasiyayuragina.mealplanner.R
import com.anastasiyayuragina.mealplanner.data.AppException

@Composable
fun ErrorDialog(exception: AppException, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = {
            Text(text = stringResource(
                id = when (exception) {
                    is AppException.NoConnection -> R.string.no_connection_error_title
                    is AppException.Id -> exception.titleId
                    else -> R.string.error
                }
            ))
        },
        text = {
            Text(text = when(exception) {
                is AppException.Id -> stringResource(id = exception.descriptionId)
                is AppException.NoConnection -> stringResource(id = R.string.no_connection_error_text)
                is AppException.Api -> exception.message.orEmpty()
                is AppException.Common -> exception.message
            })
        },
        confirmButton = {
            Button(
                onClick = { onDismiss() }
            ) {
                Text(text = stringResource(id = R.string.ok))
            }
        }
    )
}