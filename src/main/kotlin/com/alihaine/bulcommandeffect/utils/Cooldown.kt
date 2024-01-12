package com.alihaine.bulcommandeffect.utils

import java.util.*


class Cooldown(val section: String, val timer: Long) {

    companion object {
        private val cooldownList = HashMap<UUID, MutableList<Cooldown>>()

        fun isPlayerOnCoolDown(playerId: UUID): Boolean {
            return cooldownList.containsKey(playerId)
        }

        fun addPlayerCoolDown(playerId: UUID, section: String, cooldown: Int) {
            if (cooldownList[playerId] == null)
                cooldownList[playerId] = mutableListOf()
            removeIfAlreadyContain(section, cooldownList[playerId]!!)
            cooldownList[playerId]?.add(Cooldown(section, System.currentTimeMillis() + cooldown * 1000L))
        }

        private fun removeIfAlreadyContain(section: String, remindList: MutableList<Cooldown>) {
            for (remindCooldown in remindList) {
                if (remindCooldown.section == section) {
                    remindList.remove(remindCooldown)
                    return
                }
            }
        }

        fun getCoolDownTimeLeft(playerId: UUID, section: String): Long {
            if (!isPlayerOnCoolDown(playerId))
                return 0

            for (remindCooldown in cooldownList[playerId]!!) {
                if (remindCooldown.section == section)
                    return remindCooldown.timer.minus(System.currentTimeMillis()).div(1000)
            }
            return 0
        }

    }
}