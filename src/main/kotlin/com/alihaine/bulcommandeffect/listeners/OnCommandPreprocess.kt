package com.alihaine.bulcommandeffect.listeners;

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent

class OnCommandPreprocess : Listener {

    @EventHandler
    fun onPlayerChat(event: PlayerCommandPreprocessEvent) {
        println(event.message);
    }
}
