package com.hassienda.projectbootstrap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.hassienda.projectbootstrap.databinding.ActivityMainBinding
import com.hassienda.projectbootstrap.viewmodels.FormViewModel
import com.hassienda.projectbootstrap.viewmodels.MainViewModel
import com.hassienda.projectbootstrap.viewmodels.PostsViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //get the View Models
        val vm = ViewModelProviders.of(this).get(MainViewModel::class.java)
        val postsVm = ViewModelProviders.of(this).get(PostsViewModel::class.java)
        val formVm = ViewModelProviders.of(this).get(FormViewModel::class.java)

        //get the view binding
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        //set the viewmodel on the view
        binding.vm = vm
        binding.postsVm = postsVm
        binding.formVm = formVm
        //set the lifecycle owner, this allows databinding to flow
        binding.setLifecycleOwner(this)
        //set the view for the activity
        //setContentView(binding.root)
    }
}
