package com.example.mytaskactivity

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    private val adapter by lazy { // Lazy - означает что адаптер будет вызван Только когда на него обратятся вмервые до этого не вызываеться
        Adapter(
            itemClick = { position, userDetails ->
//            Log.e("ggg", "position $position, object -> $userDetails")

                val intent = Intent(this, EditInfoActivity::class.java).apply {
                    putExtra(KEY_TO_EDIT_INFO, userDetails)
                    putExtra("keyInfo1", position)
                }
                startActivityForResult(intent, KEY_REQUEST_2)


            },
            itemLongClick = { view, userDetails -> // Это колл Бэк(синим выделено)

                // V is View variable and tv is name of textView
                longClick(view, userDetails)
            }
        )
    }


    private fun longClick(list: View, userDetails: UserDetails) {

        val pop = PopupMenu(this, list)
        pop.inflate(R.menu.show_menu)

        pop.setOnMenuItemClickListener { item ->

            when (item.itemId) {
                R.id.delete -> {
                    AlertDialog.Builder(this)
                        .setTitle("Delete")
                        .setIcon(R.drawable.ic_warning)
                        .setMessage("Sure?")
                        .setPositiveButton("Yes") { dialog, _ ->
                            adapter.deleteUser(adapter.getPosition(userDetails))
                            dialog.dismiss()
                        }
                        .setNegativeButton("No") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                        .show()
                    true
                }

                R.id.editText -> {
                    val intent = Intent(this, EditInfoActivity::class.java).apply {
                        putExtra(KEY_TO_EDIT_INFO, userDetails)

                        mViewModel.loadData()

                    }
                    startActivityForResult(intent, KEY_REQUEST_2)
//                    Toast.makeText(this, "Delete work", Toast.LENGTH_SHORT)
//                        .show()

                }
                else -> true

            }
            true
        }
        pop.show()
    }

//    lateinit var mViewModel: DataMode
    lateinit var mViewModel: MainViewModel


    override fun onCreate(s: Bundle?) {
        super.onCreate(s)
        setContentView(R.layout.activity_main)

        rvListUsers.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvListUsers.adapter = adapter


//        val factory = Factory(this.applicationContext)
        mViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        mViewModel.listLD.observe(this, Observer {
            adapter.changeData(it as ArrayList<UserDetails>)
        })



        btnAddNewUser.setOnClickListener {
            val intent = Intent(this, EditInfoActivity::class.java)
            val userDetails = UserDetails()
            intent.putExtra(KEY_TO_EDIT_INFO, userDetails)
            startActivityForResult(intent, KEY_REQUEST_1)
        }


//        val loadData = mViewModel.loadData() !!!!
        adapter.changeData(mViewModel.loadData() as ArrayList<UserDetails>)

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == KEY_REQUEST_2) {
            val userDetails = data?.getSerializableExtra(EditInfoActivity.KEY_TO_MAIN_ACT) as UserDetails
            adapter.editUser(userDetails)
            mViewModel.saveData(adapter.getList())
            mViewModel.loadData()

            //  listUserDetails.add(userDetails)// Добавили в лист данные которые записали
        }
        if (requestCode == KEY_REQUEST_1 && resultCode == RESULT_OK) {
            val userDetails = data?.getSerializableExtra(EditInfoActivity.KEY_TO_MAIN_ACT) as UserDetails
//            listUserDetails.add(userDetails)// Добавили в лист данные которые записали
            adapter.addUser(userDetails) // как надо делать """""""""
            mViewModel.saveData(adapter.getList())
            mViewModel.loadData()
        }
    }

    companion object {
        const val KEY_TO_EDIT_INFO = "KEY_1"
//        const val KEY_SHARED = "KEY_SHARED"
//        const val STRING_SHARED = "STRING_SHARED"
        const val KEY_REQUEST_1 = 11
        const val KEY_REQUEST_2 = 10


    }
}


