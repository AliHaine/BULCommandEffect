package com.alihaine.bulpotioneffect.command.subcommands

import com.alihaine.bulpotioneffect.command.SubCommand
import com.alihaine.bulpotioneffect.utils.Config
import com.alihaine.bulpotioneffect.utils.Message
import org.bukkit.entity.Player

class Reload : SubCommand {
    override fun exec(sender: Player?, args: Array<out String>) {
        Config.reloadConfig()
        Message.sendMessage(sender, Message.CONFIG_RELOAD)
    }
}