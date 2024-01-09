package com.alihaine.bulcommandeffect;

import com.alihaine.bulcommandeffect.listeners.OnCommandPreprocess
import org.bukkit.plugin.java.JavaPlugin;

class BULCommandEffect : JavaPlugin() {

    override fun onEnable() {
        this.server.pluginManager.registerEvents(OnCommandPreprocess(), this);
    }

    override fun onDisable() {

    }
}
