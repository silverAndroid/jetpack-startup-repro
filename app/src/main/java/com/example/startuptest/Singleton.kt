package com.example.startuptest

import android.content.Context
import androidx.startup.Initializer
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class Singleton {
    init {
        println("Singleton initialized $this")
    }

    companion object {
        @Volatile
        private var INSTANCE: Singleton? = null

        fun newInstance(): Singleton {
            INSTANCE = Singleton()
            println("Singleton new instance $INSTANCE")
            return INSTANCE!!
        }

        fun getInstance() = INSTANCE ?: throw IllegalStateException("Instance not initialized")
    }
}

class SingletonInitializer : Initializer<Singleton> {
    override fun create(context: Context): Singleton {
        return runBlocking {
            println("SingletonInitializer")
            Singleton.newInstance()
        }
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()

}