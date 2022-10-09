package com.nurlandroid.kotapp.feature.posts

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Post(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    var page: Int
)
    : Parcelable