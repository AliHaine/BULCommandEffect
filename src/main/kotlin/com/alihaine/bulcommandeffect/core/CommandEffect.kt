package com.alihaine.bulcommandeffect.core;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

class CommandEffect(val commands: String, val potionEffects: List<Effect>, val duration: Int, val permission: String) {

    fun ApplyEffects(player: Player) {
        for (effect in potionEffects) {
            player.addPotionEffect(PotionEffect(effect.potionEffectType, duration, effect.amplifier));
        }
    }
}
