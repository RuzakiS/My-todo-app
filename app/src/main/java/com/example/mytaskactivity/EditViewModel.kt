package com.example.mytaskactivity

import android.content.Context
import android.content.SharedPreferences
import android.service.autofill.UserData
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class EditViewModel (private val context: Context) : ViewModel() {

//    val editLiveData: MutableLiveData<String>()

    private val _name: MutableLiveData<String> = MutableLiveData<String>()
    val name: LiveData<String>
        get() = _name

    private val _surname: MutableLiveData<String> = MutableLiveData<String>()
    val surname: LiveData<String>
        get() = _surname

    private val _age: MutableLiveData<String> = MutableLiveData<String>()
    val age: LiveData<String>
        get() = _age

    private val _list: MutableLiveData<String> = MutableLiveData<String>()



//    var userList : MutableLiveData<List<UserDetails>> = MutableLiveData()




    fun saveName(name: String) {
        if (_name.value != name) {
            _name.value = name
        }
    }

    fun saveSurname(surname: String) {
        if (_surname.value != surname) {
            _surname.value = surname
        }
    }

    fun saveAge(age: String) {
        if (_age.value != age) {
            _age.value = age
        }
    }




//    fun saveData() {
//
//        val sharedPreferences: SharedPreferences = context.getSharedPreferences(EDIT_VIEW_SHARED, Context.MODE_PRIVATE)
//        val editor = sharedPreferences.edit()
//
//        editor.putString(EDIT_STRING_KEY, Gson().toJson(adapter.getList()))
//        editor.apply()
//
//    }
//
//
//    fun loadData(): ArrayList<UserDetails> {
//        val sharedPreferences: SharedPreferences = context.getSharedPreferences(EDIT_VIEW_SHARED, Context.MODE_PRIVATE)
//
//        val json = sharedPreferences.getString(EDIT_STRING_KEY, "[]")
//        return Gson().fromJson(json, object : TypeToken<ArrayList<UserDetails>>() {}.type)
//
////        var listUp = adapter.getList()
////        var listUp = adapter.changeData(loadData)
//    }


    companion object {
        const val EDIT_VIEW_SHARED = "EDIT_VIEW_KEY"
        const val EDIT_STRING_KEY = "EDIT_STRING_KEY"

    }

}