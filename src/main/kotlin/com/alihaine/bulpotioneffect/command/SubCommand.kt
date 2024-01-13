package com.alihaine.bulpotioneffect.command

interface SubCommand {
    fun exec(args: Array<out String>)
}