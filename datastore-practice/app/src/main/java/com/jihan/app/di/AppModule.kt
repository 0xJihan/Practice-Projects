package com.jihan.app.di

import com.jihan.app.domain.utils.json
import okhttp3.MediaType.Companion.toMediaType
import org.koin.dsl.module
import retrofit2.converter.kotlinx.serialization.asConverterFactory


val appModule = module {
    val jsonConverterFactory = json.asConverterFactory("application/json; charset=UTF8".toMediaType())


}


