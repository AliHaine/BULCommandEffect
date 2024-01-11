package com.alihaine.bulcommandeffect.utils;

import org.bukkit.Bukkit
import org.bukkit.entity.Player

enum class Message {
    EFFECT_GIVE,
    EFFECT_DISABLE,
    EFFECT_ON_COOLDOWN,
    ERROR_CONFIG_EFFECT_EXIST,
    ERROR_EFFECT_PERMISSION,
    NO_PERMISSION;

    companion object {
        fun sendMessage(player: Player?, msg: Message) {
            val message: String? = Config.getConfigString("messages.${msg.name.lowercase()}")

            if (message.isNullOrEmpty())
                return
            if (player == null)
                Bukkit.getConsoleSender().sendMessage(message.replace('&', '§'))
            else
                player.sendMessage(message.replace('&', '§'))
        }
    }
}
