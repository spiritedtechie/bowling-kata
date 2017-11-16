data class Frame(private val number: Int) {

    private val totalPins = 10;
    private var rollResults: List<Roll> = emptyList();

    private val invalidNumberOfPins = { pinCount1: Int, pinCount2: Int ->
        pinCount1 < 0 || pinCount2 < 0
    }

    private val exceedsTotalAllowablePins = { pinCount1: Int, pinCount2: Int ->
        pinCount1 + pinCount2 > 10
    }

    private val isStrike = { pinCount1: Int, pinCount2: Int -> pinCount1 == totalPins && pinCount2 == 0 }
    private val isSpare = { pinCount1: Int, pinCount2: Int -> pinCount1 + pinCount2 == 10 }

    fun rolled(pinCount1: Int, pinCount2: Int) {
        if (invalidNumberOfPins(pinCount1, pinCount2)) throw InvalidRollException()
        if (exceedsTotalAllowablePins(pinCount1, pinCount2)) throw InvalidRollException()

        if (isStrike(pinCount1, pinCount2)) {
            addRollResult(Strike);
        } else if (isSpare(pinCount1, pinCount2)) {
            addRollResult(Spare(pinCount1, pinCount2))
        } else {
            handleOrdinaryRoll(pinCount1)
            handleOrdinaryRoll(pinCount2)
        }
    }

    private fun handleOrdinaryRoll(pinCount: Int) {
        if (pinCount == 0) {
            addRollResult(NoPinsKnockedDown)
        } else {
            addRollResult(NormalRoll(pinCount))
        }
    }

    private fun addRollResult(newRollResult: Roll) {
        rollResults += newRollResult
    }

    fun getFrameResults(): List<Roll> = rollResults
}
