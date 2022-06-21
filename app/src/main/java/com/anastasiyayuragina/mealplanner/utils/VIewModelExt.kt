package androidx.lifecycle

import com.anastasiyayuragina.mealplanner.base.Event
import com.anastasiyayuragina.mealplanner.data.toAppException
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import java.io.Closeable
import kotlin.coroutines.CoroutineContext

private const val JOB_KEY = "com.anastasiyayuragina.mealplanner.JOB_KEY"
private const val EVENTS_KEY = "com.anastasiyayuragina.mealplanner.ERROR_KEY"

/**
 * Flow for navigation and error events. To handle these events use
 * [HandleDefaultEvents][com.anastasiyayuragina.mealplanner.utils.HandleDefaultEvents]
 */
val ViewModel.events: MutableSharedFlow<Event>
    get() = getTag(EVENTS_KEY) ?: setTagIfAbsent(
        EVENTS_KEY,
        MutableSharedFlow(extraBufferCapacity = 1)
    )

/**
 * Scope that handles exceptions by mapping them to
 * [AppException][com.anastasiyayuragina.mealplanner.data.AppException] and emitting
 * [Event.ErrorDialog] to [events].
 * Use [HandleDefaultEvents][com.anastasiyayuragina.mealplanner.utils.HandleDefaultEvents] to handle these events
 */
val ViewModel.handlerScope: CoroutineScope
    get() {
        val scope: CoroutineScope? = this.getTag(JOB_KEY)
        if (scope != null) {
            return scope
        }
        return setTagIfAbsent(
            JOB_KEY,
            ClosableScope(SupervisorJob() + Dispatchers.Main.immediate + CoroutineExceptionHandler { _, error ->
                error.printStackTrace()
                events.tryEmit(Event.ErrorDialog(error.toAppException()))
            })
        )
    }

internal class ClosableScope(context: CoroutineContext) : Closeable, CoroutineScope {
    override val coroutineContext: CoroutineContext = context

    override fun close() {
        coroutineContext.cancel()
    }
}