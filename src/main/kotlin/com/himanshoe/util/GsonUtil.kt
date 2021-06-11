package com.himanshoe.util

import com.google.gson.Gson

inline fun <reified T : Any> T.asString(): String = Gson().toJson(this, T::class.java)

inline fun <reified T : Any> String.readFromString(): T = Gson().fromJson(this, T::class.java)
