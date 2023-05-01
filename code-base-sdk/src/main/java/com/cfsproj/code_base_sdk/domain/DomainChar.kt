package com.cfsproj.code_base_sdk.domain
import com.cfsproj.code_base_sdk.model.RelatedTopic
import com.cfsproj.code_base_sdk.utils.IMAGE_BASE_URL

data class DomainChar(
    val name: String,
    val description: String,
    val imageUrl: String? = null
)

fun List<RelatedTopic>?.mapToDomain(): List<DomainChar> {
    return this?.map {
        val items = it.text?.split("-") ?: emptyList()

        //TODO Review 8:30 Thu Apr 27
        DomainChar(
            name = if (items.isNotEmpty()) items[0] else "Invalid name",
            description = it.text ?: "No description",
            imageUrl = it.icon?.uRL?.run {
                "$IMAGE_BASE_URL$this"
            }
        )
    } ?: emptyList()
}