package pl.polsl.timemanager.model

import java.util.*

data class Bucket(
        val idBucket: Int? = null,
        val bucketName: String,
        val description: String,
        val creationDate: Date? = null,
        val userId: Int? = null,
        val maxTasks: Int
)