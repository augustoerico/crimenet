package com.couchbros.crimenet.enums

enum class Platform(value: Int) {

    PC(0), PS3(1), PS4(2), XBOX_360(3), XBOX_ONE(4);

    companion object Factory {
        fun fromValue(value: Int): Platform {

            var platform = PC

            when (value) {
                0 -> platform = PC
                1 -> platform = PS3
                2 -> platform = PS4
                3 -> platform = XBOX_360
                4 -> platform = XBOX_ONE
            }

            return platform
        }
    }
}
