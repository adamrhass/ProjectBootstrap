package com.hassienda.projectbootstrap.viewmodels.common

import androidx.lifecycle.MutableLiveData
import com.hassienda.projectbootstrap.repository.common.Result
import com.hassienda.projectbootstrap.viewmodels.extensions.async
import kotlinx.coroutines.Job

//Async Action is a view model that tracks and stores the
//state and result of an action with live data.
class AsyncAction<T>(val action:suspend () -> Result<T>?){
    val running = MutableLiveData<Boolean>()

    val data = MutableLiveData<T>()
    val err = MutableLiveData<Result.Error<out T>>()
    fun clearErr(){ err.value = null}

    var job: Job? = null
    fun run(){
        val j = job
        if(j != null && j.isActive) j.cancel()
        job = async{
            //try {
                running.value = true
                var res = action()
                when(res) {
                    is Result.Success -> data.value = res.data
                    is Result.Error -> err.value = res
                }
                running.value = false
//            }catch (e:Exception){
//                Log.d("AsyncAction - uncaught", e.message)
//                //err.value =
//            }
        }
    }
}
