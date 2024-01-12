package com.alihaine.bulcommandeffect.listeners

import com.alihaine.bulcommandeffect.utils.Config
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class OnPlayerJoin : Listener {

    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        if (event.player.hasPlayedBefore())
            return

        for (effect in Config.getDefaultCommandEffectList())
            effect.commandEffectDefaultApplier(event.player)
    }
}