package com.alihaine.bulcommandeffect

import com.alihaine.bulcommandeffect.listeners.OnCommandPreprocess
import com.alihaine.bulcommandeffect.listeners.OnPlayerJoin
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class BulCommandEffect : JavaPlugin() {

    companion object {
        lateinit var bulCommandEffect: BulCommandEffect
            private set
    }

    override fun onEnable() {
        bulCommandEffect = this
        this.saveDefaultConfig()

        this.server.pluginManager.registerEvents(OnCommandPreprocess(), this)
        this.server.pluginManager.registerEvents(OnPlayerJoin(), this)
        Bukkit.getConsoleSender().sendMessage("BULCommandEffect enabled")
    }

    override fun onDisable() {
        Bukkit.getConsoleSender().sendMessage("BULCommandEffect disable")
    }
}
