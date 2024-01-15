package com.alihaine.bulpotioneffect.listeners

import com.alihaine.bulpotioneffect.BulPotionEffect
import com.alihaine.bulpotioneffect.utils.Config
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerRespawnEvent
import org.bukkit.potion.PotionEffect


class OnPlayerRespawn : Listener {

    @EventHandler
    fun onPlayerRespawn(event: PlayerRespawnEvent) {
        val saveEffects: MutableCollection<PotionEffect> = event.player.activePotionEffects

        Bukkit.getScheduler().runTaskLater(BulPotionEffect.bulPotionEffect, Runnable {
            for (potionEffect in saveEffects) {
                if (Config.isStayDeathEffect(potionEffect.type))
                    event.player.addPotionEffect(potionEffect)
            }
        }, 20)
    }
}