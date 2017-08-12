package com.couchbros.crimenet.enums

import com.couchbros.crimenet.R

enum class Platform {

    PC, PS3, PS4, XBOX_360, XBOX_ONE;

    companion object Factory {
        fun fromId(value: Int): Platform {

            var platform = PC

            when (value) {
                R.id.edit_profile_pc -> platform = PC
                R.id.edit_profile_ps3 -> platform = PS3
                R.id.edit_profile_ps4 -> platform = PS4
                R.id.edit_profile_xbox360 -> platform = XBOX_360
                R.id.edit_profile_xboxone -> platform = XBOX_ONE
            }

            return platform
        }

        fun idFromString(value: String): Int {

            var id = R.id.edit_profile_pc

            when (value.trim().toUpperCase()) {
                "PS3" -> id = R.id.edit_profile_ps3
                "PS4" -> id = R.id.edit_profile_ps4
                "XBOX_360" -> id = R.id.edit_profile_xbox360
                "XBOX_ONE" -> id = R.id.edit_profile_xboxone
            }

            return id
        }
    }
}
