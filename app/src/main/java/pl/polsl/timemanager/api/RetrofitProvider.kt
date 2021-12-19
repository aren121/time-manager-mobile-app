package pl.polsl.timemanager.api

import android.util.Log
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitProvider {

    private var retrofit: Retrofit
    private var token: String? = null

    init {

        val interceptor = Interceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
                .method(original.method(), original.body())
                .also { builder ->
                    token?.let {
                        builder.addHeader("Authorization", "Bearer $it")
                    }
                }
                .build()
            Log.d("halo", "url = ${request.url().url()}")
            chain.proceed(request)
        }

        val gson = GsonBuilder()
            .setLenient()
            .create()

        val okHttpClient = OkHttpClient().newBuilder()
            .addInterceptor(interceptor)
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.0.213:4321/api/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    fun setToken(token: String?) {
        RetrofitProvider.token = token
    }

    fun <S> createService(clazz: Class<S>): S {
        return retrofit.create(clazz)
    }

}