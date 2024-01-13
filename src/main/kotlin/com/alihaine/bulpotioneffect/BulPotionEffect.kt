package com.alihaine.bulpotioneffect

import com.alihaine.bulpotioneffect.command.BPE
import com.alihaine.bulpotioneffect.listeners.OnCommandPreprocess
import com.alihaine.bulpotioneffect.listeners.OnPlayerRespawn
import com.alihaine.bulpotioneffect.listeners.OnPlayerJoin
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class BulPotionEffect : JavaPlugin() {

    companion object {
        lateinit var bulPotionEffect: BulPotionEffect
            private set
    }

    override fun onEnable() {
        bulPotionEffect = this
        this.saveDefaultConfig()

        this.server.pluginManager.registerEvents(OnCommandPreprocess(), this)
        this.server.pluginManager.registerEvents(OnPlayerJoin(), this)
        //this.server.pluginManager.registerEvents(OnPlayerRespawn(), this)

        this.getCommand("bpe")!!.setExecutor(BPE())

        Bukkit.getConsoleSender().sendMessage("BulPotionEffect enabled")
    }

    override fun onDisable() {
        Bukkit.getConsoleSender().sendMessage("BulPotionEffect disable")
    }

    fun is1_19OrHiher(): Boolean {
        var value: Int = Bukkit.getBukkitVersion()[2].digitToInt()
        if (!Bukkit.getBukkitVersion()[3].isDigit())
            return false
        value *= 10
        value += Bukkit.getBukkitVersion()[3].digitToInt()
        if (value >= 19)
            return true
        return false
    }
}
