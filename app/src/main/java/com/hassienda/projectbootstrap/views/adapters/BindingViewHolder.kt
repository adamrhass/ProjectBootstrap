package com.hassienda.projectbootstrap.views.adapters

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.hassienda.projectbootstrap.BR

class BindingViewHolder<VM,T>(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(obj: T, parentVm:VM, lo:LifecycleOwner) {
        binding.setVariable(BR.item, obj)
        binding.setVariable(BR.vm, parentVm)

        binding.setLifecycleOwner(lo)
        binding.executePendingBindings()
    }
}