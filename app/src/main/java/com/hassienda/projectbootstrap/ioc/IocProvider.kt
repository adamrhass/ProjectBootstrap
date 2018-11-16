package com.hassienda.projectbootstrap.ioc

import java.lang.Exception

//https://blog.kotlin-academy.com/dependency-injection-the-pattern-without-the-framework-33cfa9d5f312
//fun app() = AppComponent.instance

//interface AppComponent {
//
//    val bootstrapUseCases:IBootstrapManager
//
//    companion object {
//        lateinit var instance: AppComponent
//    }
//}
//
//
//
//
//fun <T>iocSet(iface:Class<T>, provider: Class<out T>, type: IocProvider.IocType = IocProvider.IocType.TRANSIENT) = IocProvider.registerProvider(iface, provider, type)
fun <T>iocSet(iface:Class<T>, providerFactory: () -> T, type: IocProvider.IocType = IocProvider.IocType.TRANSIENT) = IocProvider.registerProvider(iface, providerFactory, type)
fun <T>iocGet(iface:Class<T>):T = IocProvider.getInstance(iface)

class IocProvider {
    enum class IocType {TRANSIENT, SINGLETON, SINGLETON_LAZY }
    companion object {

        class ProviderInfo(val providerFactory:() -> Any, val type:IocType, var instance: Any? = null) //where T:Any
        //private val mappings = HashMap<Any, Any>()
        private val mappings = HashMap<Any, ProviderInfo>()
//        @JvmStatic
//        fun <T>registerProvider(iface:Class<T>, provider: Class<out T>, type:IocType = IocType.TRANSIENT){
//            //mappings[iface] = provider
//            mappings[iface] = ProviderInfo({ getInstance(iface) }, type)
//
//            //load the singleton
//            if(type == IocType.SINGLETON){
//                getInstance(iface)
//            }
//        }
        @Suppress("UNCHECKED_CAST")
        @JvmStatic
        fun <T>registerProvider(iface:Class<T>, providerFactory:() -> T, type:IocType = IocType.TRANSIENT) {
            //mappings[iface] = provider
            try{
                mappings[iface] = ProviderInfo(providerFactory as () -> Any, type)

                //load the singleton
                if(type == IocType.SINGLETON){
                    getInstance(iface)
                }
            }catch (e:Exception){
                throw Exception("providerFactory must return an instance of iface")
            }
        }
        @Suppress("UNCHECKED_CAST")
        @JvmStatic
        fun <T>getInstance(iface:Class<T>):T
        {
            mappings[iface]?.let { pi ->
                when (pi.type) {
                    IocType.TRANSIENT -> return pi.providerFactory() as T
                    else -> {
                        pi.instance?.let{inst ->
                            return inst as T
                        }

                        pi.instance = pi.providerFactory()
                        return pi.instance!! as T
                    }
                }
            }

        }


//        @JvmStatic
//        fun <T>getInstance(iface:Class<T>):T
//        {
//            mappings[iface]?.let { pi ->
//                when (pi.type) {
//                    IocType.TRANSIENT -> return getProviderType(iface).newInstance()
//                    else -> {
//                        pi.instance?.let{inst ->
//                            return inst as T
//                        }
//
//                        pi.instance = getProviderType(iface).newInstance()
//                        return pi.instance!! as T
//                    }
//                }
//            }
//
//        }
//
//        @Suppress("UNCHECKED_CAST")
//        @JvmStatic
//        fun <T>getProviderType(iface:Class<T>):Class<T>
//        {
//            mappings[iface]?.let{
//                return it.provider as Class<T>
//            }
//            throw Exception("Cannot find provider type")
//        }
    }

}