package com.example.mytaskactivity

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.Serializable

open class DataModel(private val context: Context) : ViewModel() {

//    val liveData = MutableLiveData<String>()

//    private val _name: MutableLiveData<String> = MutableLiveData<String>()
//    val name: LiveData<String>
//        get() = _name
//
//    private val _surname: MutableLiveData<String> = MutableLiveData<String>()
//    val surname: LiveData<String>
//        get() = _surname
//
//    private val _age: MutableLiveData<String> = MutableLiveData<String>()
//    val age: LiveData<String>
//        get() = _age
//
//
//    fun saveName(name: String) {
//        if (_name.value != name) {
//            _name.value = name
//        }
//    }
//
//    fun saveSurname(surname: String) {
//        if (_surname.value != surname) {
//            _surname.value = surname
//        }
//    }
//
//    fun saveAge(age: String) {
//        if (_age.value != age) {
//            _age.value = age
//        }
//    }

//    var sharedpreferences: SharedPreferences = context.getSharedPreferences(FACTORY_KEY, Context.MODE_PRIVATE)

    fun saveData() {

        val sharedPreferences: SharedPreferences = context.getSharedPreferences(KEY_SHARED, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putString(STRING_SHARED, Gson().toJson(adapter.getList()))
        editor.apply()

    }


    fun loadData(): ArrayList<UserDetails> {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(KEY_SHARED, Context.MODE_PRIVATE)

        val json = sharedPreferences.getString(STRING_SHARED, "[]")
        return Gson().fromJson(json, object : TypeToken<ArrayList<UserDetails>>() {}.type)

//        var listUp = adapter.getList()
//        var listUp = adapter.changeData(loadData)
    }


    companion object{
        const val DATAMODEL_KEY="DATAMODEL_KEY"
//        const val KEY_SHARED = "KEY_SHARED"
//        const val STRING_SHARED = "STRING_SHARED"
    }

}



