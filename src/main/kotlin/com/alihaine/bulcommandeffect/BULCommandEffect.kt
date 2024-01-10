package com.alihaine.bulcommandeffect;

import com.alihaine.bulcommandeffect.listeners.OnCommandPreprocess
import org.bukkit.plugin.java.JavaPlugin;

class BULCommandEffect : JavaPlugin() {

    companion object {
        lateinit var bulCommandEffect: BULCommandEffect
            private set
    }

    override fun onEnable() {
        bulCommandEffect = this;

        this.server.pluginManager.registerEvents(OnCommandPreprocess(), this);
    }

    override fun onDisable() {

    }
}
