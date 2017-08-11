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
    }
}
