package com.cursosandroidant.mapsbasics.common.dataAccess

import android.content.Context
import com.f8fit.gmaps_udemy.R
import com.f8fit.gmaps_udemy.common.entities.Artist
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.*
import java.lang.reflect.Type
import java.nio.charset.StandardCharsets

object FakeDatabase {
    private var result: List<Artist>? = null

    fun getArtist(context: Context): List<Artist>?{
        result?.let { return it}

        result = loadObjectsFromJson(context, R.raw.artists_list,
            object : TypeToken<ArrayList<Artist?>?>(){}.type)

        return result
    }
    
    private fun <T> loadObjectsFromJson(appContext: Context, resource: Int, jsonType: Type?): List<T>? {
        val inputStream = appContext.resources.openRawResource(resource)
        val writer: Writer = StringWriter()
        val buffer = CharArray(1024)
        try {
            val reader: Reader =
                BufferedReader(InputStreamReader(inputStream, StandardCharsets.UTF_8))
            var pointer: Int
            while (reader.read(buffer).also { pointer = it } != -1) {
                writer.write(buffer, 0, pointer)
            }
        } catch (exception: IOException) {
            exception.printStackTrace()
        } finally {
            try {
                inputStream.close()
            } catch (exception: IOException) {
                exception.printStackTrace()
            }
        }
        val jsonString = writer.toString()
        writer.close()
        val gson = Gson()
        return gson.fromJson(jsonString, jsonType)
    }
}