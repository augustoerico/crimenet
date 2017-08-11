package com.couchbros.crimenet.enums

enum class PlayerAlignment(lawChaosAxis: Int, goodEvilAxis: Int) {

    LAWFUL_GOOD(0, 0), LAWFUL_NEUTRAL(0, 1), LAWFUL_EVIL(0, 2),
    NEUTRAL_GOOD(1, 0), TRUE_NEUTRAL(1, 1), NEUTRAL_EVIL(0, 2),
    CHAOTIC_GOOD(2, 0), CHAOTIC_NEUTRAL(2, 1), CHAOTIC_EVIL(2, 2);

    companion object Factory {
        fun fromValues(lawChaosAxis: Int, goodEvilAxis: Int): PlayerAlignment {

            var alignment = TRUE_NEUTRAL

            when (lawChaosAxis)  {
                0 -> when (goodEvilAxis) {
                    0 -> alignment = LAWFUL_GOOD
                    1 -> alignment = LAWFUL_NEUTRAL
                    2 -> alignment = LAWFUL_EVIL
                }
                1 -> when (goodEvilAxis) {
                    0 -> alignment = NEUTRAL_GOOD
                    1 -> alignment = TRUE_NEUTRAL
                    2 -> alignment = NEUTRAL_EVIL
                }
                2 -> when (goodEvilAxis) {
                    0 -> alignment = CHAOTIC_GOOD
                    1 -> alignment = CHAOTIC_NEUTRAL
                    2 -> alignment = CHAOTIC_EVIL
                }
            }

            return alignment
        }
    }
}