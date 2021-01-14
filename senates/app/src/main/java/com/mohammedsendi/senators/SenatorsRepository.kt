package com.mohammedsendi.senators

import android.content.Context
import com.google.gson.GsonBuilder
import com.mohammedsendi.senators.model.Senator
import com.mohammedsendi.senators.model.SenatorDeserializer
import com.mohammedsendi.senators.model.Senators
import java.io.IOException

class SenatorsRepository private constructor(context: Context){

    private val jsonString = getJsonDataFromAsset(context,"us_senators.json")

    private val gSon = GsonBuilder().registerTypeAdapter(Senators::class.java, SenatorDeserializer()).create()
    private val senators = gSon.fromJson<Senators>(jsonString, Senators::class.java)

    fun getSenates() : List<Senator> {
        return senators.senatesList
    }

    fun getSenatorByBioId(bioId : String) : Senator? {

        return senators.senatesList.find { it.bioGuidedId == bioId }
    }

    private fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

    companion object {
        private var INSTANCE: SenatorsRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = SenatorsRepository(context)
            }
        }

        fun get(): SenatorsRepository {
            return INSTANCE ?: throw IllegalStateException("SenatorRepository must be initialized")

        }
    }

}