package com.hassienda.projectbootstrap.viewmodels.form

import androidx.lifecycle.MutableLiveData
import com.hassienda.projectbootstrap.R
import com.wajahatkarim3.easyvalidation.core.view_ktx.validEmail

open class BaseValidatableLiveData<T>:MutableLiveData<T>(){
    val isValid = MutableLiveData<Boolean>()
    val invalidReasons = MutableLiveData<List<Int>>() //string resource ids
}

class ValidatableLiveEmail:BaseValidatableLiveData<String>(){
    override fun setValue(value: String?) {
        super.setValue(value)
        if(value != null) {
            val valid = value.validEmail()
            isValid.value = valid
            if(valid){
                invalidReasons.value = arrayListOf()
            }else{
                invalidReasons.value = arrayListOf(R.string.invalidEmail)
            }
        }
    }
}