package com.alihaine.bulpotioneffect.core

import com.alihaine.bulpotioneffect.utils.*
import org.bukkit.entity.Player
import org.bukkit.potion.PotionEffect

class PotionEffect(val section: String, val commands: MutableList<String?>, private val effects: MutableList<Effect>, private val duration: Int, private val cooldown: Int, private val permission: String) {


    fun potionEffectApplier(player: Player) {
        if (permission.isNotEmpty() && !player.hasPermission(permission)) {
            Message.sendMessageComponent(player, Message.ERROR_EFFECT_PERMISSION, ComponentObj(ComponentEnum.EFFECT, section))
            return
        }

        if (isInfiniteDuration() && alreadyHaveEffects(player))
            removeEffects(player)
        else
            applyEffects(player)
    }

    fun potionEffectDefaultApplier(player: Player) {
        for (effect in effects)
            player.addPotionEffect(PotionEffect(effect.potionEffectType, duration, effect.amplifier))
    }

    private fun removeEffects(player: Player) {
        for (effect in effects) {
            player.removePotionEffect(effect.potionEffectType)
        }
        Message.sendMessageComponent(player, Message.EFFECT_DISABLE, ComponentObj(ComponentEnum.EFFECT, section))
    }

    private fun applyEffects(player: Player) {
        if (Cooldown.getCoolDownTimeLeft(player.uniqueId, section) > 0) {
            Message.sendMessageComponent(player, Message.EFFECT_ON_COOLDOWN, ComponentObj(ComponentEnum.TIME, Cooldown.getCoolDownTimeLeft(player.uniqueId, section).toString()))
            return
        }
        for (effect in effects)
            player.addPotionEffect(PotionEffect(effect.potionEffectType, duration, effect.amplifier))
        if (!player.hasPermission("bulcommandeffect.cooldown.bypass"))
            Cooldown.addPlayerCoolDown(player.uniqueId, section, cooldown)
        Message.sendMessageComponent(player, Message.EFFECT_GIVE, ComponentObj(ComponentEnum.EFFECT, section))
    }

    private fun isInfiniteDuration(): Boolean {
        return duration == Config.infinite
    }

    private fun alreadyHaveEffects(player: Player): Boolean {
        for (effect in effects) {
            if (!player.hasPotionEffect(effect.potionEffectType))
                return false
        }
        return true
    }
}
