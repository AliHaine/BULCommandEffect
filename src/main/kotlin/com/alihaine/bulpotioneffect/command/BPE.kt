package com.alihaine.bulpotioneffect.command

import com.alihaine.bulpotioneffect.command.subcommands.Give
import com.alihaine.bulpotioneffect.command.subcommands.Reload
import com.alihaine.bulpotioneffect.command.subcommands.Remove
import com.alihaine.bulpotioneffect.utils.Message
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class BPE : CommandExecutor{

    private val subCommands: HashMap<String, SubCommand> = hashMapOf()

    init {
        subCommands["give"] = Give()
        subCommands["reload"] = Reload()
        subCommands["remove"] = Remove()
    }

    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<out String>): Boolean {
        var player: Player? = null
        if (sender is Player)
            player = sender

        if (!sender.hasPermission("bulpotioneffect.admin")) {
            Message.sendMessage(player, Message.NO_PERMISSION)
            return true
        }

        if (args.isEmpty()) {
            Message.sendMessage(player, Message.ERROR_UNKNOWN_COMMAND)
            return true
        }

        val sub: SubCommand? = subCommands[args[0].lowercase()]
        if (sub == null) {
            Message.sendMessage(player, Message.ERROR_UNKNOWN_COMMAND)
            return true
        }
        sub.exec(player, args.copyOfRange(1, args.size))

        return true
    }
}