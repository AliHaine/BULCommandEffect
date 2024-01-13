package com.alihaine.bulpotioneffect.command.subcommands;

import com.alihaine.bulpotioneffect.command.SubCommand
import com.alihaine.bulpotioneffect.core.PotionEffect
import com.alihaine.bulpotioneffect.utils.ComponentEnum
import com.alihaine.bulpotioneffect.utils.ComponentObj
import com.alihaine.bulpotioneffect.utils.Config
import com.alihaine.bulpotioneffect.utils.Message
import org.bukkit.Bukkit
import org.bukkit.entity.Player

class Give : SubCommand {
    override fun exec(sender: Player?, args: Array<out String>) {
        if (args.size < 2) {
            Message.sendMessage(sender, Message.ERROR_UNKNOWN_COMMAND)
            return
        }

        val potionEffect: PotionEffect? = Config.getPotionEffectFromSection(args[1])
        if (potionEffect == null) {
            Message.sendMessage(sender, Message.ERROR_EFFECT_EXIST)
            return
        }

        val target: Player? = Bukkit.getPlayer(args[0])
        if (target == null) {
            Message.sendMessage(sender, Message.ERROR_PLAYER_EXIST)
            return
        }

        potionEffect.addEffects(target)
        Message.sendMessageComponent(sender, Message.EFFECT_GIVE, ComponentObj(ComponentEnum.EFFECT, potionEffect.section))
    }
}
