package com.couchbros.crimenet

import com.couchbros.crimenet.enums.Platform
import com.couchbros.crimenet.enums.PlayerAlignment

class Profile {

    var nickname: String = ""
    var platform: String = Platform.PC.toString()
    var alignment: String = PlayerAlignment.TRUE_NEUTRAL.toString()

}