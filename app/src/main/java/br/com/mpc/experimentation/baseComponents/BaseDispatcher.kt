package br.com.mpc.experimentation.baseComponents

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import retrofit2.Response
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

abstract class BaseDispatcher<T> : CoroutineScope {
    private val job = Job()
    private val _state: MutableLiveData<T> = MutableLiveData()
    val state: LiveData<T> get() = _state

    override val coroutineContext: CoroutineContext
        get() = MainScope().coroutineContext + job

    //Implementar depois

//    fun <R> doRequest(
//        call: suspend () -> Response<R>,
//        onError: OnError,
//        onSuccess: OnSuccess<R?>
//    ) {
//        launch(Dispatchers.IO) {
//            val response = call.invoke()
//
//            withContext(Dispatchers.Main) {
//                if (response.isSuccessful) onSuccess.invoke(response.body())
//                else onError.invoke(response.message())
//            }
//        }
//    }
//
//    protected fun emit(state: T) {
//        _state.value = state
//    }

    fun <R> doRequest(
        call: suspend () -> Response<R>
    ): TreatedResponse<R> {
        try {

            var result: TreatedResponse<R>? = null
            runBlocking(Dispatchers.IO) {
                val response = call.invoke()
                result =
                    if (response.isSuccessful) TreatedResponse(true, response = response.body())
                    else TreatedResponse(false, errorMessage = response.message())
            }
            return result!!
        } catch (e: Exception) {
            return TreatedResponse(false, errorMessage = e.message.toString())
        }
    }
}

data class TreatedResponse<R>(
    val isSuccess: Boolean,
    val response: R? = null,
    val errorMessage: String? = null
)
//
//typealias OnError = (message: String) -> Unit
//typealias OnSuccess <T> = (T) -> Unit