package com.alihaine.bulpotioneffect.utils

import com.alihaine.bulpotioneffect.BulPotionEffect
import com.alihaine.bulpotioneffect.core.PotionEffect
import com.alihaine.bulpotioneffect.core.Effect
import org.bukkit.configuration.ConfigurationSection
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.potion.PotionEffectType



class Config {
    companion object {
        val infinite: Int = setInfiniteValue()
        private val bulPotionEffect: BulPotionEffect = BulPotionEffect.bulPotionEffect
        private var config: FileConfiguration = bulPotionEffect.config
        private var effectsList: MutableList<PotionEffect> = setupEffectsList()
        private var defaultEffectsList: MutableList<String?> = setupDefaultEffectsList()
        private var stayDeathEffectsList: MutableList<String?> = setupStayDeathEffectsList()

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
            bulPotionEffect.reloadConfig()
            config = bulPotionEffect.config
            effectsList = setupEffectsList()
            defaultEffectsList = setupDefaultEffectsList()
            stayDeathEffectsList = setupStayDeathEffectsList()
        }

        private fun setupEffectsList(): MutableList<PotionEffect> {
            val section: ConfigurationSection = config.getConfigurationSection("effects") ?: return mutableListOf()
            val list: MutableList<PotionEffect> = mutableListOf()

            for (key in section.getKeys(false)) {
                val commandsList: MutableList<String?> = getConfigStringList("${section.name}.$key.commands")
                val effectsList: MutableList<Effect> = convertStringListToEffect(getConfigStringList("${section.name}.$key.effects"))
                var duration: Int = getConfigInt("${section.name}.$key.duration") * 20
                val cooldown: Int = getConfigInt("${section.name}.$key.cooldown")
                val perm: String? = getConfigString("${section.name}.$key.perm")

                if (duration == 0)
                    duration = infinite
                list.add(PotionEffect(key, commandsList, effectsList, duration, cooldown, perm.toString()))
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
            return getConfigStringList("give_by_default")
        }

        private fun setupStayDeathEffectsList(): MutableList<String?> {
            return getConfigStringList("stay_after_death")
        }

        fun getDefaultPotionEffectList(): MutableList<PotionEffect> {
            val defaultList: MutableList<PotionEffect> = mutableListOf()
            for (str in defaultEffectsList) {
                if (str == null)
                    continue
                getPotionEffectFromSection(str)?.let { defaultList.add(it) }
            }
            return defaultList
        }

        fun getPotionEffectFromCommand(command: String): PotionEffect? {
            for (commandEffect in effectsList) {
                for (cmd in commandEffect.commands)
                    if (cmd == command)
                        return commandEffect
            }
            return null
        }

        fun getPotionEffectFromSection(section: String): PotionEffect? {
            for (commandEffect in effectsList) {
                if (commandEffect.section == section)
                    return commandEffect
            }
            return null
        }

        private fun setInfiniteValue(): Int {
            if (BulPotionEffect.bulPotionEffect.is1_19OrHiher())
                return org.bukkit.potion.PotionEffect.INFINITE_DURATION
            return Int.MAX_VALUE
        }
    }
}
