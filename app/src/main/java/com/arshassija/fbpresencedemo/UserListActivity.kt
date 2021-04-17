package com.arshassija.fbpresencedemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.arshassija.fbpresencedemo.databinding.ActivitySecondBinding
import com.arshassija.fbpresencedemo.viewmodel.UserListVM
import com.arshassija.fbpresencedemo.viewmodel.UserListVMFactory

class UserListActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    private lateinit var viewModel: UserListVM
    private lateinit var adapter: UserStatusAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val name = intent.getStringExtra("name")
        viewModel = ViewModelProviders.of(this, UserListVMFactory(name!!)).get(UserListVM::class.java)
        viewModel.getUserList().observe(this,
            Observer<List<User>> {
                adapter.submitList(it)
            })

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = UserStatusAdapter()
        binding.recyclerView.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        viewModel.setMeOnline()
    }

    override fun onPause() {
        super.onPause()
        viewModel.setMeOffline()
    }

}
