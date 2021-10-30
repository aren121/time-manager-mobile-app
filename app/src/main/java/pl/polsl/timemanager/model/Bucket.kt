package pl.polsl.timemanager.model

import java.util.*

data class Bucket(
        val idBucket: Int,
        val bucketName: String,
        val description: String,
        val creationDate: Date,
        val userId: Int,
        val maxTasks: Int
)