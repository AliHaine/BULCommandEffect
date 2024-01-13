package com.alihaine.bulpotioneffect.listeners

import com.alihaine.bulpotioneffect.utils.Config
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class OnPlayerJoin : Listener {

    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        if (event.player.hasPlayedBefore())
            return

        for (effect in Config.getDefaultPotionEffectList())
            effect.addEffects(event.player)
    }
}