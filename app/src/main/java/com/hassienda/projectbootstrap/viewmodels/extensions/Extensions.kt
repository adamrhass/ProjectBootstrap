package com.hassienda.projectbootstrap.viewmodels.extensions

import kotlinx.coroutines.*

fun <T> T.async( action:suspend () -> Unit):Job where T: Any{
    return GlobalScope.launch (Dispatchers.Main) {
        action()
    }

}