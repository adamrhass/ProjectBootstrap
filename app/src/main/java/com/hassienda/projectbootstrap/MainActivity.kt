package com.hassienda.projectbootstrap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.hassienda.projectbootstrap.databinding.ActivityMainBinding
import com.hassienda.projectbootstrap.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //get the View Model
        val vm = ViewModelProviders.of(this).get(MainViewModel::class.java)

        //get the view binding
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        //set the viewmodel on the view
        binding.vm = vm
        //set the lifecycle owner, this allows databinding to flow
        binding.setLifecycleOwner(this)
        //set the view for the activity
        //setContentView(binding.root)
    }
}
