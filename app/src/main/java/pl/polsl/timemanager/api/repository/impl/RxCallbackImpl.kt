package pl.polsl.timemanager.api.repository.impl

import android.util.Log
import com.google.gson.Gson
import io.reactivex.rxjava3.core.SingleEmitter
import pl.polsl.timemanager.api.ApiError
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RxCallbackImpl<T>(private val single: SingleEmitter<Result<T>>) : Callback<T> {

    override fun onFailure(call: Call<T>, t: Throwable) {
        single.onError(t)
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        if (response.code() < 400)
            single.onSuccess(Result.success(response.body()!!))
        else {
            val message = Gson()
                    .fromJson(response.errorBody()?.string(), ApiError::class.java)?.message
            single.onSuccess(Result.failure(Throwable(message)))
        }

    }

}