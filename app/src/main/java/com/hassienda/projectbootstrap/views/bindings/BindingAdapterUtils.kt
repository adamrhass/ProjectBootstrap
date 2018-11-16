package com.hassienda.projectbootstrap.views.bindings

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hassienda.projectbootstrap.viewmodels.MainViewModel
import com.hassienda.projectbootstrap.views.adapters.GenericAdapter


class BindingAdapterUtils {
    companion object {

        @BindingAdapter(value = ["itemLayoutId", "parentVm", "items"], requireAll = true)//, requireAll = true)
        @JvmStatic
        fun <VM,T>RecyclerView.showItems(itemLayoutId:Int, parentVm:VM, items: LiveData<List<T>>) where VM:ViewModel{
            val c = context
            if (c is LifecycleOwner) {
                adapter = GenericAdapter(
                    itemLayoutId,
                    parentVm,
                    items,
                    c
                )
                layoutManager = LinearLayoutManager(context)
            }
        }
    }
}