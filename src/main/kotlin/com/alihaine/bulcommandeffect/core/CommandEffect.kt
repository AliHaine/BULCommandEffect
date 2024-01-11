package com.alihaine.bulcommandeffect.core;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

class CommandEffect(val commands: MutableList<String?>, val effects: MutableList<Effect>, val duration: Int, val permission: String) {

    fun applyEffects(player: Player) {
        if (permission.isNotEmpty() && !player.hasPermission(permission))
            return
        for (effect in effects) {
            println(effect.potionEffectType)
            player.addPotionEffect(PotionEffect(effect.potionEffectType!!, duration, effect.amplifier))
        }
    }
}
