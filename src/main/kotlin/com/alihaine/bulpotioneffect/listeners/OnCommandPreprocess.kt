package com.alihaine.bulpotioneffect.listeners

import com.alihaine.bulpotioneffect.core.PotionEffect
import com.alihaine.bulpotioneffect.utils.Config
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerCommandPreprocessEvent

class OnCommandPreprocess : Listener {

    @EventHandler
    fun onPlayerCommandPreprocess(event: PlayerCommandPreprocessEvent) {
        val commandEffect: PotionEffect = Config.getPotionEffectFromCommand(event.message) ?: return

        commandEffect.potionEffectApplier(event.player)
        event.isCancelled = true
    }
}
