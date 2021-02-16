package com.example.testbsc.datasource

import com.example.testbsc.data.Party

interface DataSource {

    fun getParty(): Party

}