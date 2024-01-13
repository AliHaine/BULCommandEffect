package com.alihaine.bulpotioneffect

import com.alihaine.bulpotioneffect.command.BPE
import com.alihaine.bulpotioneffect.listeners.OnCommandPreprocess
import com.alihaine.bulpotioneffect.listeners.OnPlayerJoin
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import java.io.IOException
import java.net.URL
import java.util.*


class BulPotionEffect : JavaPlugin() {

    companion object {
        lateinit var bulPotionEffect: BulPotionEffect
            private set
    }

    override fun onEnable() {
        //updateChecker()
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

    /*private fun updateChecker() {
        try {
            URL("https://api.spigotmc.org/legacy/update.php?resource=NUMBER").openStream().use { inputStream ->
                Scanner(inputStream).use { scanner ->
                    if (!scanner.next().equals(description.version)) {
                        Bukkit.getConsoleSender().sendMessage("------------------------------------------------------------------")
                        Bukkit.getConsoleSender().sendMessage("There is a new update available for BulPotionEffect !")
                        Bukkit.getConsoleSender().sendMessage("Download here : LINK")
                        Bukkit.getConsoleSender().sendMessage("------------------------------------------------------------------")
                    }
                }
            }
        } catch (exception: IOException) {
            logger.info("[BulPotionEffect] Cannot look for updates please contact dev in private: " + exception.message)
        }
    }*/

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
