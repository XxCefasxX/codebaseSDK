package com.cfsproj.code_base_sdk.utils

/**
 *  This class handles the type of data needed
 *  Note: If needed more implementations you only have to add more types in this enum class
 */
enum class AppType(val endPoint: String) {
    SIMPSONS("simpsons characters"),
    THE_WIRE("the wire characters")
}