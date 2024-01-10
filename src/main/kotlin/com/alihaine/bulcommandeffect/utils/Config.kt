package com.alihaine.bulcommandeffect.utils;

import com.alihaine.bulcommandeffect.BULCommandEffect
import org.bukkit.configuration.file.FileConfiguration

class Config {
    companion object {
        private val bulCommandEffect: BULCommandEffect = BULCommandEffect.bulCommandEffect;
        private var config: FileConfiguration = bulCommandEffect.config;

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
            bulCommandEffect.reloadConfig();
            config = bulCommandEffect.config;
        }
    }
}
