package com.kurt.util

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object JsonFun {
    val gson = Gson()

    inline fun <reified T : Any> fromJson(json: String): T {
        return gson.fromJson(json, T::class.java)
    }

    inline fun <reified T : Any> fromJsonList(json: String): List<T> {
        return gson.fromJson(json, object: TypeToken<List<T>>(){}.type)
    }

    inline fun <reified K : Any, reified V : Any> fromJsonMap(json: String): Map<K, V> {
        return gson.fromJson(json, object: TypeToken<Map<K, V>>(){}.type)
    }
}