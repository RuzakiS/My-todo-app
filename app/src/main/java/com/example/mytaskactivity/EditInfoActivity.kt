package com.example.mytaskactivity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_edit_info.*


class EditInfoActivity : AppCompatActivity() {



    private lateinit var person: UserDetails
    lateinit var editViewModel: EditViewModel

    override fun onCreate(s: Bundle?) {
        super.onCreate(s)
        setContentView(R.layout.activity_edit_info)



        editViewModel = ViewModelProvider(this).get(EditViewModel::class.java)

        person = intent.getSerializableExtra(MainActivity.KEY_TO_EDIT_INFO) as? UserDetails ?: UserDetails()
//        edName.setText(person.firstname)

        editViewModel.saveName(person.firstname)
        editViewModel.saveSurname(person.secondname)
        editViewModel.saveAge(person.age)
//        edAge.setText(person.age)


        editViewModel.name.observe(this, Observer {name ->
            if (name != edName.text?.toString()) {
                edName.setText(name)
                edName.setSelection(name.length) //Проверить без
            }
        })
        editViewModel.surname.observe(this, Observer {surname ->
            if (surname!= edSurname.text?.toString()) {
                edSurname.setText(surname)
                edSurname.setSelection(surname.length)
            }
        })
        editViewModel.age.observe(this, Observer {age ->
            if (age != edAge.text?.toString()) {
                edAge.setText(age)
                edAge.setSelection(age.length)
            }
        })


        btnSaveUserInfo.setOnClickListener {
            saveText()
            sendData()
            val intent = Intent().apply {
                putExtra(KEY_TO_MAIN_ACT, person)
            }
            setResult(RESULT_OK, intent)
            finish()
        }


    }
    private fun saveText() {
        btnSaveUserInfo.setOnClickListener {
            editViewModel.saveName(person.firstname)
            editViewModel.saveSurname(person.secondname)
            editViewModel.saveAge(person.age)
        }
    }


    private fun sendData() {
        person.firstname = edName.text.toString()
        person.secondname = edSurname.text.toString()
        person.age = edAge.text.toString()
    }

    companion object {
        const val KEY_TO_MAIN_ACT = "KEY_2"
    }
}

//    private fun getData(userDetails: UserDetails) {
//
//        edName.setText(userDetails.firstname)
//        edSurname.setText(userDetails.secondname)
//        edAge.setText(userDetails.age)
//    }


