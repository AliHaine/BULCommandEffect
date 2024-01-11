package com.alihaine.bulcommandeffect;

import com.alihaine.bulcommandeffect.listeners.OnCommandPreprocess
import com.alihaine.bulcommandeffect.utils.Config
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin;

class BULCommandEffect : JavaPlugin() {

    companion object {
        lateinit var bulCommandEffect: BULCommandEffect
            private set
    }

    override fun onEnable() {
        bulCommandEffect = this
        this.saveDefaultConfig();

        Config.reloadConfig()
        this.server.pluginManager.registerEvents(OnCommandPreprocess(), this)
        Bukkit.getConsoleSender().sendMessage("Enable BULcmeffect")
    }

    override fun onDisable() {

    }
}
