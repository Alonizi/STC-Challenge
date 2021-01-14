package com.mohammedsendi.senators.model

import android.util.Log
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import java.lang.reflect.Type

private const val TAG = "DESERIALIZER"

class SenatorDeserializer : JsonDeserializer<Senators> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Senators {

        json as JsonObject
        val senates = Senators()
        val senatorsList = mutableListOf<Senator>()
        val senatesObjects = json.getAsJsonArray("objects")

        senatesObjects.forEach {
            senatorsList.add(
                Senator(
                    it.asJsonObject.get("person").asJsonObject.get("bioguideid").asString,
                    it.asJsonObject.get("person").asJsonObject.get("firstname").asString,
                    it.asJsonObject.get("person").asJsonObject.get("lastname").asString,
                    it.asJsonObject.get("party").asString,
                    it.asJsonObject.get("description").asString
                )
            )
        }

        senates.senatesList = senatorsList


        return senates
    }
}