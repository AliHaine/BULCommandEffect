package com.alihaine.bulcommandeffect.listeners;

import com.alihaine.bulcommandeffect.core.CommandEffect
import com.alihaine.bulcommandeffect.utils.Config
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent

class OnCommandPreprocess : Listener {

    //todo async run ?
    @EventHandler
    fun onPlayerCommandPreprocess(event: PlayerCommandPreprocessEvent) {
        val commandEffect: CommandEffect = Config.getCommandEffect(event.message) ?: return
        commandEffect.applyEffects(event.player)
    }
}
