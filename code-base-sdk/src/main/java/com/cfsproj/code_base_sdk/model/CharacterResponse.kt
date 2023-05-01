package com.cfsproj.code_base_sdk.model


import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("Abstract")
    val `abstract`: String? = null,
    @SerializedName("AbstractSource")
    val abstractSource: String? = null,
    @SerializedName("AbstractText")
    val abstractText: String? = null,
    @SerializedName("AbstractURL")
    val abstractURL: String? = null,
    @SerializedName("Answer")
    val answer: String? = null,
    @SerializedName("AnswerType")
    val answerType: String? = null,
    @SerializedName("Definition")
    val definition: String? = null,
    @SerializedName("DefinitionSource")
    val definitionSource: String? = null,
    @SerializedName("DefinitionURL")
    val definitionURL: String? = null,
    @SerializedName("Entity")
    val entity: String? = null,
    @SerializedName("Heading")
    val heading: String? = null,
    @SerializedName("Image")
    val image: String? = null,
    @SerializedName("ImageHeight")
    val imageHeight: Int? = null,
    @SerializedName("ImageIsLogo")
    val imageIsLogo: Int? = null,
    @SerializedName("ImageWidth")
    val imageWidth: Int? = null,
    @SerializedName("Infobox")
    val infobox: String? = null,
    @SerializedName("meta")
    val meta: Meta? = null,
    @SerializedName("Redirect")
    val redirect: String? = null,
    @SerializedName("RelatedTopics")
    val relatedTopics: List<RelatedTopic>? = null,
    @SerializedName("Results")
    val results: List<Any?>? = null,
    @SerializedName("Type")
    val type: String? = null
)