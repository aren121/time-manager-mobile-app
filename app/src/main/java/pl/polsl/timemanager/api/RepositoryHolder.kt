package pl.polsl.timemanager.api

import android.util.Log
import pl.polsl.timemanager.api.repository.AuthenticationRepository
import pl.polsl.timemanager.api.repository.BucketRepository
import pl.polsl.timemanager.api.repository.impl.AuthenticationRepositoryImpl
import pl.polsl.timemanager.api.repository.impl.BucketRepositoryImpl

object RepositoryHolder {

    val authenticationRepository: AuthenticationRepository by lazy {
        AuthenticationRepositoryImpl()
    }

    val bucketRepository: BucketRepository by lazy {
        BucketRepositoryImpl()
    }

}
