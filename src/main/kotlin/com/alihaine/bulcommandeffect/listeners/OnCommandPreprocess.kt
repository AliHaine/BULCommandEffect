package com.alihaine.bulcommandeffect.listeners;

import com.alihaine.bulcommandeffect.core.CommandEffect
import com.alihaine.bulcommandeffect.utils.Config
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent

class OnCommandPreprocess : Listener {

    @EventHandler
    fun onPlayerChat(event: PlayerCommandPreprocessEvent) {

        val effectLists: MutableList<CommandEffect> = Config.test()
        effectLists.get(event.message[1].digitToInt()).ApplyEffects(event.player, effectLists.get(event.message[1].digitToInt()).potionEffects)
    }
}
