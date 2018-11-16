package com.hassienda.projectbootstrap.views.adapters

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
//private val vmFactory:(item:T?, pos:Int) -> Any
class GenericAdapter<VM,T>(private val layoutId: Int, private val parentVm:VM, private val results: LiveData<List<T>>, val lo:LifecycleOwner) : RecyclerView.Adapter<BindingViewHolder<VM,T>>() {
    init{
        results.observe(lo, Observer{
            this.notifyDataSetChanged()
        })
    }

    var bottomItemMarginDp = 72
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder<VM,T> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            layoutInflater, viewType, parent, false)
        return BindingViewHolder(binding!!)
    }

    override fun onBindViewHolder(holder: BindingViewHolder<VM,T>, position: Int) {
        val obj = getObjForPosition(position)
        holder.bind(obj, parentVm, lo)
        //add padding for last element
        if (position + 1 == itemCount) {
            // set bottom margin to 72dp.
            setBottomMargin(holder.itemView, (bottomItemMarginDp * Resources.getSystem().displayMetrics.density).toInt() )
        } else {
            // reset bottom margin back to zero. (your value may be different)
            setBottomMargin(holder.itemView, 0)
        }
    }

    fun setBottomMargin(view: View, bottomMargin: Int) {
        if (view.getLayoutParams() is ViewGroup.MarginLayoutParams) {
            val params = view.layoutParams as ViewGroup.MarginLayoutParams
            params.setMargins(params.leftMargin, params.topMargin, params.rightMargin, bottomMargin)
            view.requestLayout()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutIdForPosition(position)
    }

    fun getLayoutIdForPosition(position: Int): Int {
        return layoutId
    }

    override fun getItemCount():Int {
        var count = 0
        try{
            count = results.value?.size ?: 0
        }catch (e:Exception){
            //if this is a realm list and it is not valid, it can throw an illegal state exception
            //better to show an empty list
        }
        return count
    }

    fun getObjForPosition(position: Int): T {
        return results.value?.get(position) !!//vmFactory(results.value?.get(position), position)
    }
}