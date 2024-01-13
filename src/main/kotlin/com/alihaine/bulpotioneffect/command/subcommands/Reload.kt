package com.alihaine.bulpotioneffect.command.subcommands

import com.alihaine.bulpotioneffect.command.SubCommand
import com.alihaine.bulpotioneffect.utils.Config

class Reload : SubCommand {
    override fun exec(args: Array<out String>) {
        Config.reloadConfig()
    }
}