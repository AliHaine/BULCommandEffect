package com.alihaine.bulcommandeffect.core

import com.alihaine.bulcommandeffect.utils.ComponentEnum
import com.alihaine.bulcommandeffect.utils.ComponentObj
import com.alihaine.bulcommandeffect.utils.Cooldown
import com.alihaine.bulcommandeffect.utils.Message
import org.bukkit.entity.Player
import org.bukkit.potion.PotionEffect

const val INFINITE = 99999

class CommandEffect(val section: String, val commands: MutableList<String?>, private val effects: MutableList<Effect>, private val duration: Int, private val cooldown: Int, private val permission: String) {

    fun commandEffectApplier(player: Player) {
        if (permission.isNotEmpty() && !player.hasPermission(permission)) {
            Message.sendMessageComponent(player, Message.ERROR_EFFECT_PERMISSION, ComponentObj(ComponentEnum.EFFECT, section))
            return
        }

        if (isInfiniteDuration() && alreadyHaveEffects(player))
            removeEffects(player)
        else
            applyEffects(player)
    }

    fun commandEffectDefaultApplier(player: Player) {
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
        return duration == INFINITE
    }

    private fun alreadyHaveEffects(player: Player): Boolean {
        for (effect in effects) {
            if (!player.hasPotionEffect(effect.potionEffectType))
                return false
        }
        return true
    }
}
