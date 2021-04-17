package com.arshassija.fbpresencedemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arshassija.fbpresencedemo.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class UserListVM(var name: String) : ViewModel() {

    private val userList = MutableLiveData<List<User>>()
    private var firebaseDatabase: FirebaseDatabase? = null
    private val TAG = "UserListVM"

    init {
        firebaseDatabase = FirebaseDatabase.getInstance()
        firebaseDatabase?.getReference("members")
            ?.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.getValue(true) == null) {
                        return
                    }
                    val users = snapshot.getValue(true) as HashMap<String, String>
                    val tempList = ArrayList<User>()
                    for (user in users) {
                        tempList.add(User(user.key, user.value))
                    }
                    userList.postValue(tempList)
                }

                override fun onCancelled(error: DatabaseError) {

                }
            })
    }

    fun setMeOnline() {
        firebaseDatabase?.getReference("members/$name")?.setValue("online")
    }

    fun setMeOffline() {
        firebaseDatabase?.getReference("members/$name")?.setValue("offline")
    }

    fun getUserList(): LiveData<List<User>> {
        return userList
    }

}