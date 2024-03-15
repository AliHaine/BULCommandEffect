package com.alihaine.bulpotioneffect.utils

import org.bukkit.Bukkit
import org.bukkit.entity.Player

enum class Message() {
    EFFECT_ACTIVATE(),
    EFFECT_DISABLE(),
    EFFECT_GIVE(),
    EFFECT_REMOVE(),
    EFFECT_ON_COOLDOWN(),
    ERROR_PLAYER_EXIST(),
    ERROR_EFFECT_EXIST(),
    ERROR_CONFIG_EFFECT_EXIST(),
    ERROR_EFFECT_PERMISSION(),
    ERROR_UNKNOWN_COMMAND(),
    CONFIG_RELOAD(),
    NO_PERMISSION();

    companion object {
        fun sendMessage(player: Player?, msg: Message) {
            val message: String? = Config.getConfigString("messages.${msg.name.lowercase()}")

            if (message.isNullOrEmpty())
                return
            messageSender(player, message)
        }

        fun sendMessageComponent(player: Player?, msg: Message, component: ComponentObj) {
            var message: String? = Config.getConfigString("messages.${msg.name.lowercase()}")

            if (message.isNullOrEmpty())
                return
            message = message.replace(component.compoEnum.tag, component.value)
            messageSender(player, message)
        }

        private fun messageSender(player: Player?, message: String) {
            if (player == null)
                Bukkit.getConsoleSender().sendMessage(message.replace('&', '§'))
            else
                player.sendMessage(message.replace('&', '§'))
        }
    }
}

enum class ComponentEnum(val tag: String) {
    TIME("%time%"),
    EFFECT("%effect%"),
    POTION("%potion%");
}

class ComponentObj(val compoEnum: ComponentEnum, val value: String)
