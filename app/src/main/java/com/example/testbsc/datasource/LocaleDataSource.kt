package com.example.testbsc.datasource

import android.content.Context
import com.example.testbsc.data.Party
import com.google.gson.Gson

class LocaleDataSource(private val context: Context) : DataSource {
    override fun getParty(): Party {
        return Gson().fromJson(
            context.assets.open("party.json").bufferedReader().use { it.readText() },
            Party::class.java
        )
    }
}