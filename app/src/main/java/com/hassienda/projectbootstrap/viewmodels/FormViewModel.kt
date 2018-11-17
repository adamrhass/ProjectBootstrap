package com.hassienda.projectbootstrap.viewmodels

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hassienda.projectbootstrap.ioc.iocGet
import com.hassienda.projectbootstrap.managers.interfaces.IBootstrapManager
import com.hassienda.projectbootstrap.models.Post
import com.hassienda.projectbootstrap.viewmodels.common.AsyncAction
import com.hassienda.projectbootstrap.viewmodels.form.ValidForm
import com.hassienda.projectbootstrap.viewmodels.form.ValidatableLiveEmail
import com.hassienda.projectbootstrap.viewmodels.form.ValidatableLivePassword
import com.wajahatkarim3.easyvalidation.core.view_ktx.validEmail
import com.wajahatkarim3.easyvalidation.core.view_ktx.validator

class FormViewModel : ViewModel() {
    //Live data email with validation
    val email = ValidatableLiveEmail().also { it.setData( "username@domain.com" )  }
    val password = ValidatableLivePassword().also { it.setData("")  }
    val formIsValid = ValidForm(email, password)
    //endregion
}