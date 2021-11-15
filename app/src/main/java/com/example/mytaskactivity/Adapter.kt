package com.example.mytaskactivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.list_item.view.*

class Adapter(
    private val itemClick: (position: Int, userDetails: UserDetails) -> Unit,
    private val itemLongClick: (view: View, userDetails: UserDetails) -> Unit,
) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    private var userList = ArrayList<UserDetails>()
//    private var userinfo = UserDetails()


    fun addUser(newUser: UserDetails) {
        userList.add(newUser)
        notifyDataSetChanged()
    }

    fun deleteUser(position: Int) {
        userList.removeAt(position)
        notifyDataSetChanged()
    }

    fun getPosition(userinfo: UserDetails): Int {
        for ((index, element) in userList.withIndex()) {
            if (element.id == userinfo.id) {
                return index
            }
        }
        return -1
    }

    fun editUser(user: UserDetails) {
        val indexOf = userList.indexOfFirst { it.id == user.id }
        userList.removeAt(indexOf)
        userList.add(indexOf, user)
        notifyDataSetChanged()
    }
    fun getList(): ArrayList<UserDetails> = userList
    fun changeData(list:ArrayList<UserDetails>) {
        userList.clear()
        userList.addAll(list)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    override fun getItemCount() = userList.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(userDetails: UserDetails) {
            itemView.tvName.text = userDetails.firstname
            itemView.tvSurname.text = userDetails.secondname
            itemView.tvAge.text = userDetails.age
        }

        init {
            itemView.setOnClickListener {
                itemClick(this.adapterPosition, userList[adapterPosition])
            }
            itemView.mMenus_2.setOnLongClickListener {
                itemLongClick(it, userList[adapterPosition])//Call back
                true
            }
        }

    }

}

