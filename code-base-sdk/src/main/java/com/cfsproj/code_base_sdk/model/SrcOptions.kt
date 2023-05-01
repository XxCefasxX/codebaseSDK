package com.cfsproj.code_base_sdk.model


import com.google.gson.annotations.SerializedName

data class SrcOptions(
    @SerializedName("directory")
    val directory: String? = null,
    @SerializedName("is_fanon")
    val isFanon: Int? = null,
    @SerializedName("is_mediawiki")
    val isMediawiki: Int? = null,
    @SerializedName("is_wikipedia")
    val isWikipedia: Int? = null,
    @SerializedName("language")
    val language: String? = null,
    @SerializedName("min_abstract_length")
    val minAbstractLength: String? = null,
    @SerializedName("skip_abstract")
    val skipAbstract: Int? = null,
    @SerializedName("skip_abstract_paren")
    val skipAbstractParen: Int? = null,
    @SerializedName("skip_end")
    val skipEnd: String? = null,
    @SerializedName("skip_icon")
    val skipIcon: Int? = null,
    @SerializedName("skip_image_name")
    val skipImageName: Int? = null,
    @SerializedName("skip_qr")
    val skipQr: String? = null,
    @SerializedName("source_skip")
    val sourceSkip: String? = null,
    @SerializedName("src_info")
    val srcInfo: String? = null
)