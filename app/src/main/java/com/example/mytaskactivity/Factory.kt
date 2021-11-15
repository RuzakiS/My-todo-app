package com.example.mytaskactivity

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException


@Suppress("UNCHECKED_CAST")
class Factory(private var context: Context): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivity::class.java)) {
            return DataModel(context) as T
        }
        throw IllegalArgumentException("Viewmodel not found")
    }


}


