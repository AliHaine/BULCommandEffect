package com.alihaine.bulcommandeffect.core;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType

class CommandEffect(val commands: MutableList<String?>, val potionEffects: MutableList<Effect>, val duration: Int, val permission: String?) {

    fun ApplyEffects(player: Player, effectList: MutableList<Effect>) {
        for (effect in effectList) {
            println(effect.potionEffectType)
            player.addPotionEffect(PotionEffect(effect.potionEffectType!!, duration, effect.amplifier))
        }
    }
}
