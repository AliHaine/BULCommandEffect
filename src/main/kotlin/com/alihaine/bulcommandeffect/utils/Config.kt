package com.alihaine.bulcommandeffect.utils

import com.alihaine.bulcommandeffect.BulCommandEffect
import com.alihaine.bulcommandeffect.core.CommandEffect
import com.alihaine.bulcommandeffect.core.Effect
import com.alihaine.bulcommandeffect.core.INFINITE
import org.bukkit.configuration.ConfigurationSection
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.potion.PotionEffectType

class Config {

    companion object {
        private val bulCommandEffect: BulCommandEffect = BulCommandEffect.bulCommandEffect
        private var config: FileConfiguration = bulCommandEffect.config
        private var effectsList: MutableList<CommandEffect> = setupEffectsList()
        private var defaultEffectsList: MutableList<String?> = setupDefaultEffectsList()
        private var stayDeathEffectsList: MutableList<String> = setupStayDeathEffectsList()

        fun getConfigString(path: String): String? {
            return config.getString(path)
        }

        fun getConfigStringList(path: String): MutableList<String?> {
            return config.getStringList(path)
        }

        fun getConfigBoolean(path: String): Boolean {
            return config.getBoolean(path)
        }

        fun getConfigInt(path: String): Int {
            return config.getInt(path)
        }

        fun reloadConfig() {
            bulCommandEffect.reloadConfig()
            config = bulCommandEffect.config
            effectsList = setupEffectsList()
            defaultEffectsList = setupDefaultEffectsList()
        }

        private fun setupEffectsList(): MutableList<CommandEffect> {
            val section: ConfigurationSection = config.getConfigurationSection("effects") ?: return mutableListOf()
            val list: MutableList<CommandEffect> = mutableListOf()

            for (key in section.getKeys(false)) {
                val commandsList: MutableList<String?> = getConfigStringList("${section.name}.$key.commands")
                val effectsList: MutableList<Effect> = convertStringListToEffect(getConfigStringList("${section.name}.$key.effects"))
                var duration: Int = getConfigInt("${section.name}.$key.duration") * 20
                val cooldown: Int = getConfigInt("${section.name}.$key.cooldown")
                val perm: String? = getConfigString("${section.name}.$key.perm")

                if (duration == 0)
                    duration = INFINITE
                list.add(CommandEffect(key, commandsList, effectsList, duration, cooldown, perm.toString()))
            }
            return list
        }

        private fun convertStringListToEffect(stringList: MutableList<String?>): MutableList<Effect> {
            val effectsList: MutableList<Effect> = mutableListOf()
            var amplifier: Int

            for (str in stringList) {
                if (str == null)
                    continue
                amplifier = str.last().digitToInt() - 1

                val po: PotionEffectType? = PotionEffectType.getByName(str.dropLast(2))
                if (po == null) {
                    Message.sendMessageComponent(null, Message.ERROR_CONFIG_EFFECT_EXIST, ComponentObj(ComponentEnum.POTION, str.dropLast(2)))
                    continue
                }
                effectsList.add(Effect(po, amplifier))
            }
            return effectsList
        }

        private fun setupDefaultEffectsList(): MutableList<String?> {
            return getConfigStringList("config.give_by_default")
        }

        private fun setupStayDeathEffectsList(): MutableList<String> {
            return config.getStringList("stay_after_death")
        }

        fun getDefaultCommandEffectList(): MutableList<CommandEffect> {
            val defaultList: MutableList<CommandEffect> = mutableListOf()
            for (str in defaultEffectsList) {
                if (str == null)
                    continue
                getCommandEffectFromSection(str)?.let { defaultList.add(it) }
            }
            return defaultList
        }

        fun getCommandEffectFromCommand(command: String): CommandEffect? {
            for (commandEffect in effectsList) {
                for (cmd in commandEffect.commands)
                    if (cmd == command)
                        return commandEffect
            }
            return null
        }

        fun getCommandEffectFromSection(section: String): CommandEffect? {
            for (commandEffect in effectsList) {
                if (commandEffect.section == section)
                    return commandEffect
            }
            return null
        }
    }
}
