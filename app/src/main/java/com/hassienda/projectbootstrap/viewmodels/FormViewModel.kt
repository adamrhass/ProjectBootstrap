package com.hassienda.projectbootstrap.viewmodels

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hassienda.projectbootstrap.ioc.iocGet
import com.hassienda.projectbootstrap.managers.interfaces.IBootstrapManager
import com.hassienda.projectbootstrap.models.Post
import com.hassienda.projectbootstrap.viewmodels.common.AsyncAction
import com.hassienda.projectbootstrap.viewmodels.form.ValidatableLiveEmail
import com.wajahatkarim3.easyvalidation.core.view_ktx.validEmail
import com.wajahatkarim3.easyvalidation.core.view_ktx.validator

class FormViewModel(
    private val bootManager:IBootstrapManager = iocGet(IBootstrapManager::class.java)

):ViewModel() {
    //Live data email with validation
    val email = ValidatableLiveEmail()


    //endregion
}