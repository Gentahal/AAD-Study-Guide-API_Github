package com.idn.latihanpemahamanjson.data

import com.google.gson.annotations.SerializedName

data class RepoResponse(
    @field:SerializedName("items")
    val item: List<RepoItem>? = null
)

data class RepoItem(
    @field:SerializedName("name")
    val nama: String? = null,

    @field:SerializedName("fullname")
    val fullName: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("topics")
    val topics: List<String>

)