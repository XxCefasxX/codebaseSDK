package com.cfsproj.code_base_sdk.model


import com.google.gson.annotations.SerializedName

data class Meta(
    @SerializedName("attribution")
    val attribution: Any? = null,
    @SerializedName("blockgroup")
    val blockgroup: Any? = null,
    @SerializedName("created_date")
    val createdDate: Any? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("designer")
    val designer: Any? = null,
    @SerializedName("dev_date")
    val devDate: Any? = null,
    @SerializedName("dev_milestone")
    val devMilestone: String? = null,
    @SerializedName("developer")
    val developer: List<Developer?>? = null,
    @SerializedName("example_query")
    val exampleQuery: String? = null,
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("is_stackexchange")
    val isStackexchange: Any? = null,
    @SerializedName("js_callback_name")
    val jsCallbackName: String? = null,
    @SerializedName("live_date")
    val liveDate: Any? = null,
    @SerializedName("maintainer")
    val maintainer: Maintainer? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("perl_module")
    val perlModule: String? = null,
    @SerializedName("producer")
    val producer: Any? = null,
    @SerializedName("production_state")
    val productionState: String? = null,
    @SerializedName("repo")
    val repo: String? = null,
    @SerializedName("signal_from")
    val signalFrom: String? = null,
    @SerializedName("src_domain")
    val srcDomain: String? = null,
    @SerializedName("src_id")
    val srcId: Int? = null,
    @SerializedName("src_name")
    val srcName: String? = null,
    @SerializedName("src_options")
    val srcOptions: SrcOptions? = null,
    @SerializedName("src_url")
    val srcUrl: Any? = null,
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("tab")
    val tab: String? = null,
    @SerializedName("topic")
    val topic: List<String?>? = null,
    @SerializedName("unsafe")
    val unsafe: Int? = null
)