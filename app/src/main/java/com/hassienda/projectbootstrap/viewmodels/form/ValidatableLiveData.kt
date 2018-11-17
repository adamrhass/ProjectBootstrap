package com.hassienda.projectbootstrap.viewmodels.form

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.hassienda.projectbootstrap.R
import com.wajahatkarim3.easyvalidation.core.view_ktx.atleastOneLowerCase
import com.wajahatkarim3.easyvalidation.core.view_ktx.validEmail
import com.wajahatkarim3.easyvalidation.core.view_ktx.validator
import java.util.logging.Logger

abstract class BaseValidatableLiveData<T>{
    val data = MutableLiveData<T>()
    val isValid = MutableLiveData<Boolean>()
    val reason = MutableLiveData<String>() //string resource ids
    fun setData(value: T?) {
        data.value = value
        if(value != null) {
            val valid = validate(value)
            isValid.value = valid
            isValidListener?.invoke(valid)
        }
    }
    abstract fun validate(d:T?):Boolean
    var isValidListener:((isValid:Boolean) -> Unit)? = null
    fun onIsValidChanged(listener:((isValid:Boolean) -> Unit)?){
        isValidListener = listener
    }

}

class ValidatableLiveEmail:BaseValidatableLiveData<String>(){
    override fun validate(d: String?): Boolean {
        val valid = (d ?: "").validEmail{
            reason.value = it
        }
        return valid
    }
}
class ValidatableLivePassword:BaseValidatableLiveData<String>(){
    override fun validate(d: String?): Boolean {
        var valid = false
        (d ?: "")
            .validator()
            .atleastOneLowerCase()
            .atleastOneUpperCase()
            .atleastOneNumber()
            .atleastOneSpecialCharacters()
            .addErrorCallback{
                reason.value = it
            }
            .addSuccessCallback {
                reason.value = null
                valid = true
            }.check()

        return valid
    }
}

class ValidForm(vararg val validatables:BaseValidatableLiveData<*>):MutableLiveData<Boolean>(){

    init{
        for(v in validatables){
            v.onIsValidChanged { update() }
        }
    }
    fun update(){
        var isValid = true
        for(v in validatables){
            if(v.isValid.value != true){
                isValid = false
            }
        }
        value = isValid
    }
}