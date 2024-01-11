package com.alihaine.bulcommandeffect.listeners;

import com.alihaine.bulcommandeffect.core.CommandEffect
import com.alihaine.bulcommandeffect.utils.Config
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent

class OnCommandPreprocess : Listener {

    @EventHandler
    fun onPlayerCommandPreprocess(event: PlayerCommandPreprocessEvent) {
        val commandEffect: CommandEffect = Config.getCommandEffect(event.message) ?: return

        commandEffect.commandEffectApplier(event.player)
        event.isCancelled = true
    }
}
