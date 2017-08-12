package com.couchbros.crimenet.enums

enum class PlayerAlignment {

    LAWFUL_GOOD, LAWFUL_NEUTRAL, LAWFUL_EVIL,
    NEUTRAL_GOOD, TRUE_NEUTRAL, NEUTRAL_EVIL,
    CHAOTIC_GOOD, CHAOTIC_NEUTRAL, CHAOTIC_EVIL;

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

        fun axisValues(alignment: PlayerAlignment): Pair<Int, Int> {

            var values = Pair(1, 1)

            when (alignment) {
                LAWFUL_GOOD -> values = Pair(0, 0)
                LAWFUL_NEUTRAL -> values = Pair(0, 1)
                LAWFUL_EVIL -> values = Pair(0, 2)

                NEUTRAL_GOOD -> values = Pair(1, 0)
                TRUE_NEUTRAL -> values = Pair(1, 1)
                NEUTRAL_EVIL -> values = Pair(1, 2)

                CHAOTIC_GOOD -> values = Pair(2, 0)
                CHAOTIC_NEUTRAL -> values = Pair(2, 1)
                CHAOTIC_EVIL -> values = Pair(2, 2)
            }

            return values
        }
    }
}