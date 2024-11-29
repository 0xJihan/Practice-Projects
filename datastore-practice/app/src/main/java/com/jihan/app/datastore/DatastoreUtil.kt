package com.jihan.app.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class DatastoreUtil(private val context: Context) {
    private val Context.datastore by preferencesDataStore("local")

     suspend fun saveData(key: String, value: String) {
         context.datastore.edit { preferences ->
             preferences[stringPreferencesKey(key)] = value
         }

     }

         suspend fun readData(key: String): String? {
             val preferences = context.datastore.data.first()
             return preferences[stringPreferencesKey(key)]
         }




}