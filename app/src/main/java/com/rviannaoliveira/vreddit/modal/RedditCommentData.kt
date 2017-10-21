package com.rviannaoliveira.vreddit.modal

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import com.rviannaoliveira.vreddit.util.DefaultData

/**
 * Criado por rodrigo on 18/10/17.
 */
@Entity(tableName = "comments")
data class RedditCommentData(
        @PrimaryKey(autoGenerate = true)
        var idRoom: Int = DefaultData.getInt(),
        var id: String = DefaultData.getString(),
        var author: String = DefaultData.getString(),
        var created: Long = DefaultData.getLong(),
        var body: String = DefaultData.getString()) {

    @Ignore
    constructor() : this(DefaultData.getInt(), DefaultData.getString(), DefaultData.getString(), DefaultData.getLong(), DefaultData.getString())
}
