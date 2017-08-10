package com.couchbros.crimenet.enums

enum class PlayerAlignment(lawChaosAxis: Int, goodEvilAxis: Int) {

    LAWFUL_GOOD(0, 0), LAWFUL_NEUTRAL(0, 1), LAWFUL_EVIL(0, 2),
    NEUTRAL_GOOD(1, 0), TRUE_NEUTRAL(1, 1), NEUTRAL_EVIL(0, 2),
    CHAOTIC_GOOD(2, 0), CHAOTIC_NEUTRAL(2, 1), CHAOTIC_EVIL(2, 2);

    companion object Factory {
        fun create(lawChaosAxis: Int, goodEvilAxis: Int): PlayerAlignment {

            if (lawChaosAxis == 0 && goodEvilAxis == 0) return LAWFUL_GOOD
            else if (lawChaosAxis == 0 && goodEvilAxis == 1) return LAWFUL_NEUTRAL
            else if (lawChaosAxis == 0 && goodEvilAxis == 2) return LAWFUL_EVIL

            else if (lawChaosAxis == 1 && goodEvilAxis == 0) return NEUTRAL_GOOD
            else if (lawChaosAxis == 1 && goodEvilAxis == 2) return NEUTRAL_EVIL

            else if (lawChaosAxis == 2 && goodEvilAxis == 0) return CHAOTIC_GOOD
            else if (lawChaosAxis == 2 && goodEvilAxis == 1) return CHAOTIC_NEUTRAL
            else if (lawChaosAxis == 2 && goodEvilAxis == 2) return CHAOTIC_EVIL

            return TRUE_NEUTRAL
        }
    }
}