package kr.bomiza.universe.common.util

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder

class GsonUtils {
    companion object {
        private val gson = GsonBuilder()
            .setFieldNamingStrategy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()

        fun getLowerCaseWithUnderscores(): Gson {
            return gson
        }
    }
}