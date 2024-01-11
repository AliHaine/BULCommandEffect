package com.alihaine.bulcommandeffect.utils

import java.util.*


class Cooldown {

    companion object {
        private val cooldownList = HashMap<UUID, Long>()

        fun isPlayerOnCoolDown(playerId: UUID): Boolean {
            return cooldownList.containsKey(playerId)
        }

        fun addPlayerCoolDown(playerId: UUID, cooldown: Int) {
            cooldownList[playerId] = System.currentTimeMillis() + cooldown * 1000L
        }

        fun removePlayerCoolDown(playerId: UUID) {
            cooldownList.remove(playerId)
        }

        fun getCoolDownTimeLeft(playerId: UUID): Long {
            return (cooldownList[playerId]?.minus(System.currentTimeMillis()))?.div(1000) ?: return 0
        }

    }
}