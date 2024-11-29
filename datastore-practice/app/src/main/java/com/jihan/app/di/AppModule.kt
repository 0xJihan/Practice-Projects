package com.jihan.app.di


import com.jihan.app.datastore.DatastoreUtil
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val appModule = module {

 single {
     DatastoreUtil(androidContext())
 }

}


