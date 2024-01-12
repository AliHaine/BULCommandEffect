package com.alihaine.bulpotioneffect.utils

import org.bukkit.Bukkit
import org.bukkit.entity.Player

enum class Message(val key: String) {
    EFFECT_GIVE("effect_give"),
    EFFECT_DISABLE("effect_disable"),
    EFFECT_ON_COOLDOWN("effect_on_cooldown"),
    ERROR_CONFIG_EFFECT_EXIST("error_config_effect_exist"),
    ERROR_EFFECT_PERMISSION("error_effect_permission"),
    NO_PERMISSION("no_permission");

    companion object {
        fun sendMessage(player: Player?, msg: Message) {
            val message: String? = Config.getConfigString("messages.${msg.key}")

            if (message.isNullOrEmpty())
                return
            messageSender(player, message)
        }

        fun sendMessageComponent(player: Player?, msg: Message, component: ComponentObj) {
            var message: String? = Config.getConfigString("messages.${msg.key}")

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
