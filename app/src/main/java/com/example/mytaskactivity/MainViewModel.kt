package com.example.mytaskactivity

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.Serializable

open class MainViewModel(private val context: Context) : ViewModel() {



    private val _listLD: MutableLiveData<String> = MutableLiveData<String>()
    val listLD: LiveData<String>
        get() = _listLD


    fun saveData(list: List<UserDetails>){
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(MAIN_SHARED_MODEL, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()


        editor.putString(MAIN_STRING_MODEL, Gson().toJson(list))
        editor.apply()
    }


    fun loadData() {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(MAIN_SHARED_MODEL, Context.MODE_PRIVATE)

        val json = sharedPreferences.getString(MAIN_STRING_MODEL, "[]")
        val list: List<UserDetails> = Gson().fromJson(json, object : TypeToken<ArrayList<UserDetails>>() {}.type)
        _listLD.value = list.toString()
    }








    companion object {
        const val MAIN_SHARED_MODEL = "MAIN_SHARED_MODEL"
        const val MAIN_STRING_MODEL = "MAIN_STRING_MODEL"
    }
}