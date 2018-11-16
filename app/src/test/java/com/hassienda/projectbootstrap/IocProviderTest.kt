package com.hassienda.projectbootstrap

import com.hassienda.projectbootstrap.api.IPostsService
import com.hassienda.projectbootstrap.ioc.IocProvider
import com.hassienda.projectbootstrap.ioc.iocGet
import com.hassienda.projectbootstrap.ioc.iocSet
import com.hassienda.projectbootstrap.managers.BootstrapManager
import com.hassienda.projectbootstrap.repository.interfaces.IPostsRepo
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mockito
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */


class IocProviderTest {
    //region Dummy classes
    interface IDummy{ val uuid:UUID }
    class Dummy:IDummy{
        override val uuid:UUID = UUID.randomUUID()
    }
    //endregion


    @Test
    fun test_transient() {
        iocSet(IDummy::class.java, { Dummy() }, IocProvider.IocType.TRANSIENT)
        assertNotEquals(iocGet(IDummy::class.java).uuid, iocGet(IDummy::class.java).uuid)
    }

    @Test
    fun test_singletonLazy() {
        iocSet(IDummy::class.java, { Dummy() }, IocProvider.IocType.SINGLETON_LAZY)
        assertEquals(iocGet(IDummy::class.java).uuid, iocGet(IDummy::class.java).uuid)
    }

    @Test
    fun test_singleton() {
        iocSet(IDummy::class.java, { Dummy() }, IocProvider.IocType.SINGLETON)
        assertEquals(iocGet(IDummy::class.java).uuid, iocGet(IDummy::class.java).uuid)
    }


}
