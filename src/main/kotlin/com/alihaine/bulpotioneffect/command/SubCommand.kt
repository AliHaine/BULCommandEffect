package com.alihaine.bulpotioneffect.command

import org.bukkit.entity.Player

interface SubCommand {
    fun exec(sender: Player?, args: Array<out String>)
}